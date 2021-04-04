package com.abuzar.locationsearch.utils

import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.data.Coordinates


fun getMockCitiesList(): ArrayList<CityModel> {


    val citiesList = ArrayList<CityModel>()

    citiesList.add(CityModel(707860, "UA", "Hurzuf", Coordinates(34.283333, 44.549999)))
    citiesList.add(CityModel(519188, "RU", "Novinki", Coordinates(37.666668, 55.683334)))
    citiesList.add(CityModel(1283378, "NP", "Gorkhā", Coordinates(84.633331, 28.0)))
    citiesList.add(CityModel(1270260, "IN", "State of Haryāna", Coordinates(76.0, 29.0)))
    citiesList.add(CityModel(708546, "UA", "Holubynka", Coordinates(33.900002, 44.599998)))
    citiesList.add(CityModel(707860, "NP", "Bāgmatī Zone", Coordinates(85.416664, 28.0)))
    citiesList.add(CityModel(529334, "RU", "Mar’ina Roshcha", Coordinates(34.283333, 55.796391)))
    citiesList.add(CityModel(1269750, "IN", "Republic of India", Coordinates(77.0, 20.0)))
    citiesList.add(CityModel(1283240, "NP", "Kathmandu", Coordinates(85.316666, 27.716667)))
    citiesList.add(CityModel(703363, "UA", "Laspi", Coordinates(33.733334, 44.416668)))
    citiesList.add(CityModel(3632308, "VE", "Merida", Coordinates(-71.144997, 8.598333)))
    citiesList.add(CityModel(473537, "RU", "Vinogradovo", Coordinates(38.545555, 55.423332)))
    citiesList.add(CityModel(384848, "IQ", "Qarah Gawl al ‘Ulyā", Coordinates(45.6325, 35.353889)))
    citiesList.add(CityModel(569143, "RU", "Cherkizovo", Coordinates(37.728889, 55.800835)))
    citiesList.add(CityModel(713514, "UA", "Alupka", Coordinates(34.049999, 44.416668)))
    citiesList.add(CityModel(2878044, "DE", "Lichtenrade", Coordinates(13.40637, 52.398441)))
    citiesList.add(CityModel(464176, "RU", "Zavety Il’icha", Coordinates(37.849998, 56.049999)))
    citiesList.add(CityModel(295582, "IL", "Azriqam", Coordinates(34.700001, 31.75)))
    citiesList.add(CityModel(1271231, "IN", "Ghūra", Coordinates(79.883331, 24.766666)))
    citiesList.add(CityModel(690856, "UA", "Tyuzler", Coordinates(34.083332, 44.466667)))
    citiesList.add(CityModel(464737, "RU", "Zaponor’ye", Coordinates(38.861942, 55.639999)))
    citiesList.add(CityModel(707716, "UA", "Il’ichëvka", Coordinates(34.383331, 44.666668)))
    citiesList.add(CityModel(697959, "UA", "Partyzans’ke", Coordinates(34.083332, 44.833332)))

    return citiesList

}

fun getMockSortedCitiesList(): ArrayList<CityModel> {

    val citiesList = ArrayList<CityModel>()
    citiesList.add(CityModel(713514, "UA", "Alupka", Coordinates(34.049999, 44.416668)))
    citiesList.add(CityModel(295582, "IL", "Azriqam", Coordinates(34.700001, 31.75)))
    citiesList.add(CityModel(707860, "NP", "Bāgmatī Zone", Coordinates(85.416664, 28.0)))
    citiesList.add(CityModel(569143, "RU", "Cherkizovo", Coordinates(37.728889, 55.800835)))
    citiesList.add(CityModel(1271231, "IN", "Ghūra", Coordinates(79.883331, 24.766666)))
    citiesList.add(CityModel(1283378, "NP", "Gorkhā", Coordinates(84.633331, 28.0)))
    citiesList.add(CityModel(708546, "UA", "Holubynka", Coordinates(33.900002, 44.599998)))
    citiesList.add(CityModel(707860, "UA", "Hurzuf", Coordinates(34.283333, 44.549999)))
    citiesList.add(CityModel(707716, "UA", "Il’ichëvka", Coordinates(34.383331, 44.666668)))
    citiesList.add(CityModel(1283240, "NP", "Kathmandu", Coordinates(85.316666, 27.716667)))
    citiesList.add(CityModel(703363, "UA", "Laspi", Coordinates(33.733334, 44.416668)))
    citiesList.add(CityModel(2878044, "DE", "Lichtenrade", Coordinates(13.40637, 52.398441)))
    citiesList.add(CityModel(529334, "RU", "Mar’ina Roshcha", Coordinates(34.283333, 55.796391)))
    citiesList.add(CityModel(3632308, "VE", "Merida", Coordinates(-71.144997, 8.598333)))
    citiesList.add(CityModel(519188, "RU", "Novinki", Coordinates(37.666668, 55.683334)))
    citiesList.add(CityModel(697959, "UA", "Partyzans’ke", Coordinates(34.083332, 44.833332)))
    citiesList.add(CityModel(384848, "IQ", "Qarah Gawl al ‘Ulyā", Coordinates(45.6325, 35.353889)))
    citiesList.add(CityModel(1269750, "IN", "Republic of India", Coordinates(77.0, 20.0)))
    citiesList.add(CityModel(1270260, "IN", "State of Haryāna", Coordinates(76.0, 29.0)))
    citiesList.add(CityModel(690856, "UA", "Tyuzler", Coordinates(34.083332, 44.466667)))
    citiesList.add(CityModel(473537, "RU", "Vinogradovo", Coordinates(38.545555, 55.423332)))
    citiesList.add(CityModel(464737, "RU", "Zaponor’ye", Coordinates(38.861942, 55.639999)))
    citiesList.add(CityModel(464176, "RU", "Zavety Il’icha", Coordinates(37.849998, 56.049999)))

    return citiesList

}

