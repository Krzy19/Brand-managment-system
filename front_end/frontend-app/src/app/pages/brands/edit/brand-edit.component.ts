import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrandService } from '../../../services/brand.service';
import { Brand } from '../../../models/brand';


@Component({
    selector: 'app-brand-edit',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './brand-edit.component.html'
})
export class BrandEditComponent implements OnInit
{
    brandId!: string;
    brand: Brand = { id: '', brandName: '', dateOfEstablishment: '', country: '' };

    constructor(private route: ActivatedRoute, private brandService: BrandService, private router: Router) {}

    ngOnInit()
    {
        this.brandId = this.route.snapshot.params['id'];
        this.brandService.getById(this.brandId).subscribe(b => this.brand = b);
    }

    save()
    {
        this.brandService.update(this.brandId, this.brand).subscribe(() => {this.router.navigate(['/brands']);});
    }
}
