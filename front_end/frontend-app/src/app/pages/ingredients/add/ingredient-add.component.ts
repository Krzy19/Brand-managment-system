import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IngredientService } from '../../../services/ingredient.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-ingredient-add',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './ingredient-add.component.html'
})

export class IngredientAddComponent
{
    name = '';
    brandId!: string;
    brandName!: string;
    errorMessage = '';

    flavor = '';
    sparkling = false;

    constructor(private route: ActivatedRoute, private ingredientService:
    IngredientService, private router: Router)
    {
        this.brandId = this.route.snapshot.params['id'];
        this.brandName = this.route.snapshot.params['name'];
    }

    save()
    {
        this.errorMessage = '';
        const payload = { id: '', flavor: this.flavor, sparkling: this.sparkling, brandId: this.brandId, brandName: this.brandName };

        this.ingredientService.add(payload).subscribe({
            next: (res) => {
                console.log('Ingredient added', res);
                this.router.navigate(['/brands', this.brandId]);
            },
            error: (err) => {
                console.error('Error adding ingredient', err);
                this.errorMessage = `Error adding ingredient: ${err.message || err}`;
            }
        });
    }

}
