import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrandService } from '../../../services/brand.service';


@Component({
    selector: 'app-brand-add',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './brand-add.component.html'
})
export class BrandAddComponent
{
    brandName = '';
    dateOfEstablishment = '';
    country = '';

    constructor(private brandService: BrandService, private router: Router) {}

    save()
    {
        this.brandService.add({
          id: '',
          brandName: this.brandName,
          dateOfEstablishment: this.dateOfEstablishment,
          country: this.country
        })
      .subscribe({
        next: () => this.router.navigate(['/brands']),
        error: () => this.router.navigate(['/brands'])
      });

    }
}
