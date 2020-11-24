# Used Car Inventory

## Project description

**The Functionality:**

This application is a used car inventory that showcases pre-existing vehicles that have been listed. The user can also
add additional vehicles to the inventory if needed. Additionally, this app will allow the user to search for a vehicle 
listed in the inventory which will then return cars based on the user's interests. 

**The Features:**
- Search and return function that gives the use a list of cars based on their specifications.
- Listings must specify whether a car is stock or modified.
- Input a range of years instead of a single year to return cars of a specific "generation".

**The Audience:**

This app is for people who are in the market to buy a used vehicle. However, **it is aimed 
towards individuals that want to buy used cars for the purpose of modifying or building them.** 

**The Why:**

Currently, many of the popular marketplaces (e.g. craigslist, facebook marketplace) for used cars take away from the 
experience of users who want to buy them for the purpose of modifying and making it their own. This is true as these 
marketplaces throw unnecessary listings when you search for a specific model of a car, making the process more 
time-consuming and difficult. That being said, this project is of interest to me as I want to create the optimal
experience for individuals looking for their next car to work on.

## User Stories
- As a user, I want to be able to search for a vehicle with the exact specifications I am interested in.
- As a user, I want to be able to view a list of vehicles that meet the exact specifications I am interested in.
- **As a user, I want to be able to list my own vehicles up for sale.**
- As a user, I want to be able to see the vehicles I have listed.
- As a user, I want save my vehicle listings to file
- As a user, I want to be able to load my listings from file

## GUI User Stories
- As a user, I want to be able to list a vehicle and have it appear in the list of vehicles.
- As a user, I want the option to view the vehicles I listed as well as all the current vehicle listings.
- As a user, I want to be able to save and load my listings anytime I'm using the application.

## Phase 4: Task 2
- For phase 4 I chose to test and design the Vehicle class to be robust.
- The Vehicle class, and the constructor now have a robust design. This is because I created an exception for the
previous requires clause of the constructor that specified odometer cannot be negative. I also wrote tests for the 
exception to ensure that it was working properly.

## Phase 4: Task 3
- create a superclass for myListings and allListings so that there is less duplicated code. This is because, currently, 
the Vehicles class holds two lists of vehicles which have methods with the same functionality which can be refactored to
a super class and then have myListings and allListings extend it.
- Can also refactor methods like searchListings() in vehicles for readability. For instance, I can make a new method in
place for the if statements conditional as it is quite difficult to read.