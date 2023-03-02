import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Apartment } from "../models/apartment.model";
import {map, Observable} from "rxjs";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class ApartmentsService {

  private apartmentUrl = `${environment.apiUrl}/apartments`;

  constructor(private http: HttpClient) {}

  getApartments(): Observable<Apartment[]> {
    return this.http.get<any[]>(this.apartmentUrl).pipe(
      map(apartments => apartments.map(apartment => {
        return ApartmentHelper.createApartment(apartment);
      }))
    );
  }

  getApartment(id: number) {
    const apartmentUrl = `${this.apartmentUrl}/${id}`;
    return this.http.get<Apartment>(apartmentUrl).pipe(
      map(apartment => {
        return ApartmentHelper.createApartment(apartment);
      })
    );
  }

  addNewApartment(newApartmentDto: NewApartmentDto) {
    return this.http.post(this.apartmentUrl, newApartmentDto);
  }
}
