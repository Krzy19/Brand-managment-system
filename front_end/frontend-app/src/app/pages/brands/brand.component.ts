import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Brand } from '../../models/brand';
import { BrandService } from '../../services/brand.service';
import { Router } from '@angular/router';
import { timeout } from 'rxjs/operators';


@Component({
selector: 'app-categories-list',
standalone: true,
imports: [CommonModule, RouterModule],
templateUrl: './brand.component.html'
})

export class BrandComponent implements OnInit
{
  brands: Brand[] = [];
  isLoading = true;
  errorMessage = '';

  constructor(private brandService: BrandService, private router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void
  {
    console.log('brandService.ngOnInit() called');
    this.load();
  }

  load(): void
  {
    console.log('Fetching brands...');
    this.isLoading = true;
    this.errorMessage = '';

    this.brandService.getAll().
    pipe(timeout(10000)).
    subscribe({

      next: (data)=>
      {
          console.log('Brands loaded!');
          this.brands=data;
          this.isLoading=false;
          this.cdr.markForCheck();
      },
      error: (err) =>
      {
        console.error('Failed to load brands.',err);
        this.errorMessage = `Error loading brands: ${err.message || err}`;
        this.isLoading = false;
        this.cdr.markForCheck();
      }

      })
  }

  delete(id: string)
  {
    this.brandService.delete(id).subscribe({
        next: () => this.load(),
        error: () => this.load()
      });
  }

  goEdit(id: string)
  {
    this.router.navigate(['/brands', id, 'edit']);
  }

  goAdd()
  {
    this.router.navigate(['/brands/add']);
  }

  goDetails(id: string)
  {
    this.router.navigate(['/brands', id]);
  }

}
