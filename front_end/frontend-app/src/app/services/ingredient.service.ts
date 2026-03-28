import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable , throwError, of} from 'rxjs';
import { Ingredient } from '../models/ingredient';
import { catchError, switchMap } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class IngredientService {

  private apiUrl = `${environment.apiUrl}/ingredients`;

  constructor(private http: HttpClient) {}

  getForBrand(brandId: string): Observable<Ingredient[]> {
    return this.http
      .get<Ingredient[]>(`${this.apiUrl}?brandId=${brandId}`)
      .pipe(
        catchError(() => of([]))
      );
  }

    getById(id: string): Observable<Ingredient> {
      return this.http.get<Ingredient>(`${this.apiUrl}/${id}`);
    }

    add(ingredient: Partial<Ingredient>): Observable<Ingredient> {
      return this.http.post<Ingredient>(this.apiUrl, {
        flavor: ingredient.flavor,
        sparkling: Boolean(ingredient.sparkling),
        brandId: ingredient.brandId || ingredient.brand?.id
      });
    }

    update(id: string, ingredient: Ingredient): Observable<void> {
      return this.http.put<void>(`${this.apiUrl}/${id}`, ingredient);
    }

    delete(id: string): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
