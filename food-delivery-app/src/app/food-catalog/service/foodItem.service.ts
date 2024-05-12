import { Injectable } from "@angular/core";
import { API_URL_FC } from "../../constrants/url";
import { HttpClient } from "@angular/common/http";
import { Observable, catchError, throwError } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class FoodItemService {
    
    private apiUrl = API_URL_FC+'/foodCatalog/fetchRestaurantAndFoodItemById/';

    constructor(private http: HttpClient) { }

    getFoodItemsByRestaurant(id: number): Observable<any> {
        return this.http.get<any>(`${this.apiUrl+id}`)
        .pipe(
            catchError(this.handleError)
        );
    }
    private handleError(error: any) {
        console.error('An error occured:', error);
        return throwError(error.message || error);
    }
    

}