import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderModule } from './header/header.module';
import { HeaderComponent } from './header/component/header.component';
import { RestaurantListingComponent } from './restaurant-listing/component/restaurant-listing.component';
import { RestaurantListingModule } from './restaurant-listing/restaurant-listing.module';

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    imports: [RouterOutlet, HeaderModule, HeaderComponent, RestaurantListingModule]
})
export class AppComponent {
  title = 'food-delivery-app';
}
