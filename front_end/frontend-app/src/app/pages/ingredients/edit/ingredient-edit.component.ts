import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IngredientService } from '../../../services/ingredient.service';
import { Ingredient } from '../../../models/ingredient';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-ingredient-edit',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './ingredient-edit.component.html'
})
export class IngredientEditComponent implements OnInit
{
    brandId!: string;
    elementId!: string;
    ingredient: Ingredient = { id: '', flavor: '', sparkling: false };
    constructor(
    private route: ActivatedRoute,
    private ingredientService: IngredientService,
    private router: Router
    ) {}

    ngOnInit()
    {
        this.brandId = this.route.snapshot.params['id'];
        this.elementId = this.route.snapshot.params['elementId'];
        this.ingredientService.getById(this.elementId).subscribe((i: Ingredient) => this.ingredient = i);
    }

    save()
    {
        this.ingredientService.update(this.elementId,this.ingredient).subscribe(() =>
        {
          this.router.navigate(['/brands', this.brandId]);
        });
    }
}
