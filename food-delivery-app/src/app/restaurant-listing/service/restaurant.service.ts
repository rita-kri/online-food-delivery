import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, throwError } from "rxjs";
import { API_URL_RL } from "../../constrants/url";

@Injectable ({
    providedIn: 'root'
})
export class RestaurantService {
   
    private apiUrl = API_URL_RL+'/restaurant/allRestaurants';

    constructor(private http : HttpClient){ }

    getAllRestaurants(): Observable<any> {
        return this.http.get<any>(`${this.apiUrl}`)
        .pipe(
            catchError(this.handleError)
        );
    }

    private handleError(error: any) {
        console.error('An error occured:', error);
        return throwError(error.message || error);
    }
}
    

