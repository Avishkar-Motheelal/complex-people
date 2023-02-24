-- Create Database
CREATE DATABASE ComplexPeople
GO
	
USE ComplexPeople
GO

--Create Tables
CREATE TABLE [DocumentTypes] (
  [DocumentTypesId] INT IDENTITY(1,1) NOT NULL,
  [Type] NVARCHAR(255) NOT NULL,
  PRIMARY KEY ([DocumentTypesId])
);

CREATE TABLE [IdentificationDocuments] (
  [IdentificationDocumentsId] INT IDENTITY(1,1) NOT NULL,
  [Number] NVARCHAR(35) NOT NULL,
  [DocumentTypesId] INT NOT NULL,
  PRIMARY KEY ([IdentificationDocumentsId]),
  FOREIGN KEY ([DocumentTypesId]) REFERENCES [DocumentTypes]([DocumentTypesId])
);

CREATE TABLE [Photos] (
  [PhotosId] INT IDENTITY(1,1) NOT NULL,
  [PhotoUrl] NVARCHAR(Max) NOT NULL,
  PRIMARY KEY ([PhotosId])
);

CREATE TABLE [ContactDetails] (
  [ContactDetailsId] INT IDENTITY(1,1) NOT NULL,
  [PhoneNumber] NVARCHAR(15) NOT NULL,
  [EmailAddress] NVARCHAR(255) NULL,
  PRIMARY KEY ([ContactDetailsId])
);

CREATE TABLE [People] (
  [PeopleId] INT IDENTITY(1,1) NOT NULL,
  [FirstName] NVARCHAR(50) NOT NULL,
  [LastName] NVARCHAR(50) NOT NULL,
  [IdentificationDocumentsId] INT NOT NULL,
  [ContactDetailsId] INT,
  PRIMARY KEY ([PeopleId]),
  FOREIGN KEY ([IdentificationDocumentsId]) REFERENCES IdentificationDocuments([IdentificationDocumentsId]),
  FOREIGN KEY ([ContactDetailsId]) REFERENCES ContactDetails([ContactDetailsId])
);

CREATE TABLE [Roles] (
  [RolesId] INT IDENTITY(1,1) NOT NULL,
  [Type] NVARCHAR(255) NOT NULL,
  PRIMARY KEY ([RolesId])
);

CREATE TABLE [RolesPeople] (
  [RolesPeopleId] INT IDENTITY(1,1) NOT NULL,
  [PeopleId] INT NOT NULL,
  [RolesId] INT NOT NULL,
  PRIMARY KEY ([RolesPeopleId]),
  FOREIGN KEY ([PeopleId]) REFERENCES People([PeopleId]),
  FOREIGN KEY ([RolesId]) REFERENCES Roles([RolesId])
);

CREATE TABLE [Apartments] (
  [ApartmentsId] INT IDENTITY(1,1) NOT NULL,
  [UnitNumber] VARCHAR(35) NOT NULL,
  PRIMARY KEY ([ApartmentsId])
);

CREATE TABLE [Visits] (
  [VisitsId] INT IDENTITY(1,1) NOT NULL,
  [VisitorId] INT NOT NULL,
  [ApartmentsId] INT NOT NULL,
  [DateIn] DATETIMEOFFSET NOT NULL,
  [DateOut] DATETIMEOFFSET NULL,
  [PhotosId] INT,
  PRIMARY KEY ([VisitsId]),
  FOREIGN KEY ([VisitorId]) REFERENCES People([PeopleId]),
  FOREIGN KEY ([ApartmentsId]) REFERENCES Apartments([ApartmentsId]),
  FOREIGN KEY ([PhotosId]) REFERENCES Photos([PhotosId])
);

CREATE TABLE [ApartmentsPeople] (
  [ApartmentsPeopleId] INT IDENTITY(1,1) NOT NULL,
  [PeopleId] INT NOT NULL,
  [ApartmentsId] INT NOT NULL,
  PRIMARY KEY ([ApartmentsPeopleId]),
  FOREIGN KEY ([ApartmentsId]) REFERENCES Apartments([ApartmentsId]),
  FOREIGN KEY ([PeopleId]) REFERENCES People([PeopleId])
);

CREATE TABLE [AccessCards] (
  [AccessCardsId] UNIQUEIDENTIFIER NOT NULL,
  [Activated] BIT NOT NULL,
  [PeopleId] INT NOT NULL,
  PRIMARY KEY ([AccessCardsId]),
  FOREIGN KEY ([PeopleId]) REFERENCES People([PeopleId])
);

CREATE TABLE [ComplaintTypes] (
  [ComplaintTypesId] INT IDENTITY(1,1) NOT NULL,
  [Type] NVARCHAR(255) NOT NULL,
  PRIMARY KEY ([ComplaintTypesId])
);

CREATE TABLE [Status] (
  [StatusId] INT IDENTITY(1,1) NOT NULL,
  [Status] NVARCHAR(50) NOT NULL,
  PRIMARY KEY ([StatusId])
);

CREATE TABLE [Complaints] (
  [ComplaintsId] INT IDENTITY(1,1) NOT NULL,
  [ComplaintTypesId] INT NOT NULL,
  [Description] NVARCHAR(MAX) NOT NULL,
  [Date] DATETIME NOT NULL,
  [StatusId] INT NOT NULL,
  [Complainant] INT NOT NULL,
  [Respondent] INT NULL,
  PRIMARY KEY ([ComplaintsId]),
  FOREIGN KEY ([ComplaintTypesId]) REFERENCES ComplaintTypes([ComplaintTypesId]),
  FOREIGN KEY ([StatusId]) REFERENCES Status([StatusId]),
  FOREIGN KEY ([Complainant]) REFERENCES ApartmentsPeople([ApartmentsPeopleId]),
  FOREIGN KEY ([Respondent]) REFERENCES People([PeopleId])
);