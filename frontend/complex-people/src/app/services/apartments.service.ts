import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Apartment} from "../models/apartment.model";
import {Observable} from "rxjs";
import {environment} from "../environments/environment";

// import {environment} from "../../environments/environment.ts";


@Injectable({
  providedIn: 'root'
})
export class ApartmentsService {

  private apartmentUrl = `${environment.apiUrl}/apartments`;

  constructor(private http: HttpClient) {
  }

  getApartments(): Observable<Apartment[]> {
    return this.http.get<Apartment[]>(this.apartmentUrl);
  }

  getApartment(id: number) {
    const apartmentUrl = `${this.apartmentUrl}/${id}`;
    return this.http.get<Apartment>(apartmentUrl);
  }

}
