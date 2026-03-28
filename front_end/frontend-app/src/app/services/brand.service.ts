import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Brand } from '../models/brand';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class BrandService {
  private apiUrl = `${environment.apiUrl}/brands`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Brand[]> {
    return this.http.get<Brand[]>(this.apiUrl);
  }

  getById(id: string): Observable<Brand> {
    return this.http.get<Brand>(`${this.apiUrl}/${id}`);
  }

  add(brand: Brand): Observable<Brand> {
    return this.http.post<Brand>(this.apiUrl, brand);
  }

  update(id: string, brand: Brand): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}`, brand);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
