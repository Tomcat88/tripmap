# Tripmap

A simple tool that creates a png file of a trip based on a JSON input.
The tool uses the direction and the static map API offered by Google.

## Installation

The project uses Leiningen, so in order to build the standalone jar you have to use the following command in the root folder of the project:
    lein uberjar

## Usage

The tool must receive one or more path of JSONs in input to work properly

    $ java -jar tripmap-0.1.0-standalone.jar [args]

The input JSON must be something like this one :

    {
        "start" : "12/12/1999",
        "end" : "13/12/1999",
        "options":{
                "width" : 1920,
                "height" : 1080,
                "output" : "/home/thomas/Documenti/trips/trip1.png"
        },
        "trip" : {
               "from" : "Cuggiono",
               "to" : "Cuggiono",
               "waypoints" : ["Zurich","Konstanz","Freiburg","Karlsruhe","Basel"]
        }
    }

Right now the start and end attributes are not used.
