package th.ac.kku.cis.mobileapp.StudentActivity


class Activity{  //กิจกรรม
    companion object Factory {
        fun create(): Activity = Activity()
    }

    var ActivityId: String? = null
    var AcID: String? = null
    var NameActivity: String? = null
    var UnitActivity: String? = null

}

class Student {//นักศึกษา

    companion object Factory1 {
        fun create(): Student = Student()
    }
    var NewName: String? = null
    var Id: String? = null
    var NameStudent: String? = null
    var IdStudent: String? = null
}