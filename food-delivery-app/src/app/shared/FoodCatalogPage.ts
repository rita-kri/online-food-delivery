import { FoodItem } from "./models/FoodItem";
import { Restaurant } from "./models/Restaurant";

export interface FoodCatalogPage {
    foodItemList: FoodItem[];
    restaurant: Restaurant;
}