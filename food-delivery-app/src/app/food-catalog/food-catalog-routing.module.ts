import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FoodCatalogComponent } from './component/food-catalog.component';

const routes: Routes = [
  { path: '/food-catalog/:id', component: FoodCatalogComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FoodCatalogRoutingModule { }
