import { Routes } from '@angular/router';
import { BrandComponent } from './pages/brands/brand.component';
import { BrandAddComponent } from './pages/brands/add/brand-add.component';
import { BrandDetailsComponent } from './pages/brands/details/brand-details.component'
import { BrandEditComponent } from './pages/brands/edit/brand-edit.component'

import { IngredientAddComponent } from './pages/ingredients/add/ingredient-add.component';
import { IngredientComponent } from './pages/ingredients/ingredient.component';
import { IngredientEditComponent } from './pages/ingredients/edit/ingredient-edit.component';

//import { IngredientsComponent } from './pages/ingredients/ingredient.component';

export const routes: Routes = [
  { path: '', redirectTo: 'brands', pathMatch: 'full' },
  { path: 'brands', component: BrandComponent },
  { path: 'brands/add', component: BrandAddComponent},
  { path: 'brands/:id', component: BrandDetailsComponent},
  { path: 'brands/:id/edit', component: BrandEditComponent},

  { path: 'brands/:id/ingredients/add', component: IngredientAddComponent},
  { path: 'brands/:id/ingredients/:elementId', component: IngredientComponent},
  { path: 'brands/:id/ingredients/:elementId/edit', component: IngredientEditComponent},
  //{ path: 'brands/:id/ingredients', component: IngredientsComponent },
  { path: '**', redirectTo: 'brands' }
];
