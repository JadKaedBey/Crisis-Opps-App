package com.example.crisisopp.home.models

import java.io.Serializable

/**
 * PcrForm Class
 * Child of IForm parent class
 */

data class PcrForm(
    var fullName: String = "",
    var mothersName: String = "",
    var birthDate: String = "",
    var bloodType: String = "",
    var placeOfResidence: String = "",
    var dateOfInfection: String? = "",
    var recordNumber: Int = 0,
    var phoneNumber: String = "",
    var nameOfSource: String = "",
    var originatorId: String = "",
    var ainWzeinApproval: Int = 0,
    var additionalNotes: String = "",
    var municipalityName: String = "",
    override var dateOfUpload: Int = 1,
    override var formType: String = "",
    override var formID: String = "",
    override var appointment: String = ""
) : IForm, Serializable {
    companion object {
        const val FIELD_FORM_NAME = "fullName"
        const val FIELD_FORM_PHONE_NUMBER = "phoneNumber"
        const val FIELD_FORM_PLACE_OF_RESEDENCY = "placeOfResidence"
        const val FIELD_FORM_NAME_OF_SOURCE = "nameOfSource"
        const val FIELD_FORM_BLOOD_TYPE = "bloodType"
    }
}



