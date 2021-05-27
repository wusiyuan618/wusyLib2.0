package com.wusy.wusypro.app

object URLUtil{
    var baseUrl = ""
    var comParam: String = "&platform=Android"
    var pageSize = Contants.pageSize

    /**
     * 获取城市列表
     * 初始传0
     */
    fun getProgramList(pageIndex:Int,programName:String): String {
        return "${baseUrl}cgProgramApi/program/getPageMini?pageIndex=${pageIndex}&pageSize=${pageSize}" +
                "&employeeId=0592489b42b742ad8dcf1210b9698b9d&employeeType=1&programName=${programName}&comId=586238e5f33b4788b0d4663b18de33d4" +
                "&queryCount=true&focusEmployeeId=0592489b42b742ad8dcf1210b9698b9d&platform=Android$comParam"
    }


}
