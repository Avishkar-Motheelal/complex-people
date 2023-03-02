export interface NewVisitorDto {

  "firstName": string,
  "lastName": string,
  "phoneNumber": string,
  "emailAddress": string,
  "identificationDocument": {
    "number": string,
    "documentType": string
  },
  "apartmentId": number,

  "role": {
    "roleType": string;
  }

}
