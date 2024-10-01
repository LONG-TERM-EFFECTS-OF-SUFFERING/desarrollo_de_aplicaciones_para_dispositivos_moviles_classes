package com.example.classes.class_03


class Class03Student(var name: String, var course: String, var grade_1: Float, var grade_2: Float,
                     var repeated_course: Boolean) {

   fun calculate_result() : Float {
        var result = (grade_1 + grade_2) / 2

        if (repeated_course)
            result -= 0.5f

        return result
    }
}
