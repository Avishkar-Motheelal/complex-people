import {Person} from "../models/person.model";
import {Apartment} from "../models/apartment.model";
import {PersonHelper} from "./person-helper";

export class ApartmentHelper {
  static createApartment(apartment: any): Apartment {
    return new class implements Apartment {
      apartmentsId: number = apartment.apartmentsId;
      bathrooms: number = apartment.bathrooms;
      bedrooms: number = apartment.bedrooms;
      parkingSpaces: number = apartment.parkingSpaces;
      people: Person[] = apartment.people.map((person: any) => {
        return PersonHelper.createPerson(person);
      });
      unitNumber: number = apartment.unitNumber;
    }
  }
}
