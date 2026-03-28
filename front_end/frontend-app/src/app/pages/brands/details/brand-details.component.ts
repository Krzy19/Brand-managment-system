import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { BrandService } from '../../../services/brand.service';
import { IngredientService } from '../../../services/ingredient.service';
import { Brand } from '../../../models/brand';
import { Ingredient } from '../../../models/ingredient';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
    selector: 'brand-details',
    standalone: true,
    imports: [CommonModule, RouterModule],
    templateUrl: './brand-details.component.html'
})
export class BrandDetailsComponent implements OnInit
{

  brandId!: string;
  brand?: Brand;
  ingredients: Ingredient[] = [];
  isLoading = true;
  errorMessage = '';
  private paramSub: any;

  constructor
  (
    private route: ActivatedRoute,
    private brandService: BrandService,
    private ingredientService: IngredientService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ){}

  ngOnInit()
  {
    this.paramSub = this.route.paramMap.subscribe((params: ParamMap) => {

      const id =params.get('id');

      if(!id) return;

      this.brandId=id;
      console.log(this.brandId, ' loaded!');
      this.loadData();
      }
    )
  }

  ngOnDestroy()
  {
    if (this.paramSub) this.paramSub.unsubscribe();
  }

  private loadData()
  {
    this.isLoading = true;
    this.errorMessage = '';
    this.brand = undefined;
    this.ingredients = [];

    this.brandService.getById(this.brandId).subscribe(
      {
                next: (b) => { this.brand = b; console.log('Loaded brand', b); this.cdr.detectChanges(); },
                error: (err) => { console.error('Error loading brand', err); this.errorMessage = 'Error loading brand'; this.cdr.detectChanges(); }
      });

    this.ingredientService.getForBrand(this.brandId).subscribe(
      {
                next: (i) => { console.log('Loaded ingredients', i); this.ingredients = i; this.isLoading = false; this.cdr.detectChanges(); },
                error: (err: HttpErrorResponse) => { console.error('Error loading ingredients', err); this.errorMessage = `Error loading ingredients: ${err.message || err}`; this.isLoading = false; this.cdr.detectChanges(); }
      });
  }

  deleteElement(id: string)
  {
    this.ingredientService.delete(id).subscribe(() => {
      this.loadData();
      });
  }

  addElement()
  {
    this.router.navigate(['/brands', this.brandId, 'ingredients', 'add']);
  }

  goElement(id: string)
  {
    this.router.navigate(['/brands', this.brandId, 'ingredients', id]);
  }

  goEditElement(id: string)
  {
    this.router.navigate(['/brands', this.brandId, 'ingredients', id, 'edit']);
  }

  goBack() {
    this.router.navigate(['/brands']);
  }
}
