import { Component } from '@angular/core';
import { Restaurant } from '../../shared/models/Restaurant';
import { RestaurantService } from '../service/restaurant.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-restaurant-listing',
  templateUrl: './restaurant-listing.component.html',
  styleUrl: './restaurant-listing.component.css'
})
export class RestaurantListingComponent {

  public restaurantList: Restaurant[];

  ngOnInit(){
    this.getAllRestaurants();
  }
  
  constructor(private router: Router, private restaurantService: RestaurantService){ }

  getAllRestaurants() {
    this.restaurantService.getAllRestaurants().subscribe(
      data => {
        this.restaurantList = data;
      }
    )   
  }
  
  getRandomImage(): string {
    const imageCount = 8; //Adjust this number based on the number of images in our assets
    const randomIndex = this.getRandomNumber(1, imageCount);
    return `${randomIndex}.jpg`;//Replace with our image filename pattern
  }
  getRandomNumber(min: number, max: number): number {
    return Math.floor(Math.random() * (max-min +1)) +min;
  }

  onButtonClick(id: number | undefined) {
    if (id !== undefined) {
      this.router.navigate(['/food-catalog', id]);
    } else {
      // Handle the case when id is undefined
      console.error('ID is undefined');
    }
  }
}


