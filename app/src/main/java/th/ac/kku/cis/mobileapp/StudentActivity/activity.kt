package th.ac.kku.cis.mobileapp.StudentActivity


class Activity{
    companion object Factory {
        fun create(): Activity = Activity()
    }

    var ActivityId: String? = null
    var NameActivity: String? = null
    var UnitActivity: String? = null

}

class Student {

    companion object Factory1 {
        fun create(): Student = Student()
    }

    var Id: String? = null
    var NameStudent: String? = null
    var IdStudent: String? = null
}