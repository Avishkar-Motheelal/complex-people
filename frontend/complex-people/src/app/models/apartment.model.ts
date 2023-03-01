import {Person} from "./person.model";

export interface Apartment {
  apartmentsId: number,
  unitNumber: number,
  bedrooms: number,
  bathrooms: number,
  parkingSpaces: number
  people: Person[]
}
