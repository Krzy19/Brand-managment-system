import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IngredientService } from '../../services/ingredient.service';
import { Ingredient } from '../../models/ingredient';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
    selector: 'app-element-details',
    standalone: true,
    imports: [CommonModule, RouterModule],
    templateUrl: './ingredient.component.html'
})
export class IngredientComponent implements OnInit
{
    brandId!: string;
    brandName!: string;
    elementId!: string;
    ingredient?: Ingredient;
    isLoading = true;
    errorMessage = '';

    constructor(
    private route: ActivatedRoute,
    private ingredientService: IngredientService,
    private router: Router,
    private cdr: ChangeDetectorRef
    ) {}

    ngOnInit()
    {
        this.brandId = this.route.snapshot.params['id'];
        this.brandName = this.route.snapshot.params['name'];
        this.elementId = this.route.snapshot.params['elementId'];
        this.isLoading = true;
        this.errorMessage = '';
        this.ingredientService.getById(this.elementId).subscribe({
            next: (i: Ingredient) => {
                console.log('Loaded ingredient:', i);
                this.ingredient = i;
                this.isLoading = false;
                this.cdr.detectChanges();
            },
            error: (err) => {
                console.error('Error loading ingredient:', err);
                this.errorMessage = `Error loading ingredient: ${err.message || err}`;
                this.isLoading = false;
                this.cdr.detectChanges();
            }
        });
    }

  goBack()
  {
      this.router.navigate(['/brands',this.brandId]);
    }
}
