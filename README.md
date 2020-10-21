# COVID-19 Data Visualizer


This application allows the user to visualize COVID-19 case data for each of the states in the USA.
It allows the user to compare all of the state's new deaths, new cases or total number of active cases per 1000 people (in order to account for population) by colour coding states that fall within certain ranges. The user can also select a certain state and it will provide more in depth data for that state.

All of the data fetching and processing is done server-side. The front end creates GET (HTTP) requests to either:

* Determine the number of cases / new cases / new deaths for all of the states so that they can be colored appropriately.
* Get in-depth information for a given state


## Data Sources:

* Covid case data was sourced from “The COVID Tracking Project” API  <https://covidtracking.com/> 
* State population data was sourced from the census bureau <https://www.census.gov>
* State name to abbreviation data was sourced from Social Security Administration <https://www.ssa.gov>
* GeoJson data for the USA was sourced from <https://eric.clst.org/>

## Tools Used:

* Spring Boot
* Leaflet
* Java
* JavaScript
* HTML
* CSS



![picture alt](https://i.imgur.com/OxC9PGF.png)
