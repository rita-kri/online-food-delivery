import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FoodCatalogRoutingModule } from './food-catalog-routing.module';
import { FoodCatalogComponent } from './component/food-catalog.component';


@NgModule({
  declarations: [FoodCatalogComponent],
  imports: [
    CommonModule,
    FoodCatalogRoutingModule
  ]
})
export class FoodCatalogModule { }
