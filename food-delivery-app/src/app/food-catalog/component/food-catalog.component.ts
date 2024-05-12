import { Component } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import { FoodItemService } from '../service/foodItem.service';
import { FoodCatalogPage } from '../../shared/FoodCatalogPage';
import { FoodItem } from '../../shared/models/FoodItem';


@Component({
  selector: 'app-food-catalog',
  templateUrl: './food-catalog.component.html',
  styleUrl: './food-catalog.component.css'
})
export class FoodCatalogComponent {
  restaurantId: number;
  foodItemResponse: FoodCatalogPage ;
  foodItemCart: FoodItem[] = [];
  orderSummary: FoodCatalogPage;

  constructor(private route: ActivatedRoute, private foodItemService: FoodItemService, private router: Router){ }

  ngOnInit(){
    this.route.paramMap.subscribe(params => {
      this.restaurantId = +params.get('id');
    });
  
  this.getFoodItemsByRestaurant(this.restaurantId);
  }
  getFoodItemsByRestaurant(restaurant: number) {
    this.foodItemService.getFoodItemsByRestaurant(this.restaurantId).subscribe(
      data => {
        this.foodItemResponse = data;
      }
    )
  }

  increment(food: any) {
    food.quantity++;
    const index = this.foodItemCart.findIndex(item => item.id === food.id);
    if(index === -1) {
      //if record does not exist, add it to the array
      this.foodItemCart.push(food);
    }else{
      //if record exists, update it to the array
      this.foodItemCart[index] = food;
    }
  }

  decrement(food: any) {
    if(food.quantity > 0) {
      food.quantity--;

      const index = this.foodItemCart.findIndex(item => item.id === food.id);
      if(this.foodItemCart[index].quantity == 0) {
        this.foodItemCart.splice(index, 1);

      } else {
        this.foodItemCart[index] = food;
      }
    }
  }

  onCheckOut() {
    this.foodItemCart;
    this.orderSummary = {
      foodItemList: [],
      restaurant: null
    }
    this.orderSummary.foodItemList = this.foodItemCart;
    this.orderSummary.restaurant = this.foodItemResponse.restaurant;
    this.router.navigate(['/orderSummary'], { queryParams: { data: JSON.stringify(this.orderSummary)}} )
  }
}
