package com.decagonhq.clads.data.entity

class Specialty(var name: String, var checked: Boolean) {

    companion object {
        fun getDefaultSpecialityList(): ArrayList<Specialty> {

            return arrayListOf(
                Specialty("Yoruba Attires", false),
                Specialty("Hausa attires", false),
                Specialty("Senator", false),
                Specialty("Embroidery", false),
                Specialty("African fashion", false),
                Specialty("School Uniforms", false),
                Specialty("Malitary and paramilitary Uniforms", false),
                Specialty("Igbo attires", false),
                Specialty("Southsouth attires", false),
                Specialty("Kaftans", false),
                Specialty("Contemporary", false),
                Specialty("Western Fashion", false),
                Specialty("Caps", false)
            )
        }
    }
}
