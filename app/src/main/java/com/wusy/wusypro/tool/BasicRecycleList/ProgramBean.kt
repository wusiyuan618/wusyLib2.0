package com.wusy.wusypro.tool.BasicRecycleList

import com.wusy.wusypro.app.BasicBean

class ProgramBean:BasicBean(){
    var data: DataBean? = null

    class DataBean {
        /**
         * totalNum : 139
         * rows : [{"status":"1","createTime":1615256285007,"updateTime":1620637154020,"createBy":"2641e4119f714b928453c4d854a6fc58","updateBy":"e2d85bdd87fe463ab7abf2aeaedf8e9e","id":"7541d6107ccb44b89fac86672b99d3dd","financialNumber":"G202103003","comId":"586238e5f33b4788b0d4663b18de33d4","partyaName":"贤铭建设有限公司","programName":"武汉万科东风村项目K1地块精装修工程","location":"13,180,1547","locationName":"湖北,武汉,硚口区","address":"湖北省武汉市硚口区古田二路东风村","money":"25000000","startTime":"2021-03-15","endTime":"2022-12-31","programManagerId":"d5f2b575bc8a4c2987e97c18b611ece7","area":"","totalBudgetCost":"","rateType":"","programStatus":"1","signType":"","programType":"1","programImg":"","checkLabourerType":"","mpSign":"2","mpVacation":"2","formFeeRate":"5","programFeeRate":"2","feeRateStatus":"1","checkinStatus":"0","focusStatus":"0","locationxy":"30.61067278396578,114.18556772891475","mpSignRadius":"","remainingTime":584,"joinTime":0,"nodeInCount":0,"nodeOutCount":0,"checkinAllCount":0,"checkinCount":0,"checkinCountCompareYestoday":0,"reportCount":0,"imgCount":0},{"status":"1","createTime":1595306809767,"updateTime":1620636374683,"createBy":"e2d1b1b65c8c4a91a8ac49386715a31f","updateBy":"e2d85bdd87fe463ab7abf2aeaedf8e9e","id":"66ba30b073b74471a63afc8498f3f042","financialNumber":"G202007006","comId":"586238e5f33b4788b0d4663b18de33d4","partyaName":"厦门鑫熙筑置业有限公司","programName":"厦门龙湖东孚南路项目户内及公区精装修工程","location":"4,60,588","locationName":"福建,厦门,海沧区","address":"厦门市海沧区新阳西片区东孚二路与东孚南路交叉东南","money":"43709803.83","startTime":"2020-06-15","endTime":"2022-11-30","programManagerId":"3f77e0eca0a044788fd96a62d7a135f5","area":"","totalBudgetCost":"","rateType":"","programStatus":"1","signType":"","programType":"1","programImg":"/ows-worker/deviceBehand/7efa9f7c1eed4b6a82e24e0cb89ec651.png,/ows-worker/deviceBehand/3e8bb52c91b7410aa5a55dcc6ec1e87f.png,/ows-worker/deviceBehand/b3f335d3b2d14481962b2c03e1528568.jpg","checkLabourerType":"","mpSign":"2","mpVacation":"2","formFeeRate":"5","programFeeRate":"2","feeRateStatus":"1","checkinStatus":"0","focusStatus":"0","locationxy":"24.53121224377697,117.97202709097678","mpSignRadius":"","remainingTime":553,"joinTime":0,"nodeInCount":0,"nodeOutCount":0,"checkinAllCount":4,"checkinCount":0,"checkinCountCompareYestoday":0,"reportCount":0,"imgCount":0},{"status":"1","createTime":1617067512715,"updateTime":1620637253426,"createBy":"2641e4119f714b928453c4d854a6fc58","updateBy":"e2d85bdd87fe463ab7abf2aeaedf8e9e","id":"c846a3cb10e940ec9d84a9d920301e6c","financialNumber":"G202101001","comId":"586238e5f33b4788b0d4663b18de33d4","partyaName":"重庆溪畔置业有限公司","programName":"重庆万科江北区溉澜溪组团G13-1地块标段一户内及公区精装修工程","location":"32,394,3338","locationName":"重庆,重庆,江北区","address":"重庆市江北区新溉大道溉澜溪组团G13-1地块","money":"23884595.17","startTime":"2021-03-01","endTime":"2022-03-19","programManagerId":"49c14cf5ac98445d83ac5277890b08c8","area":"","totalBudgetCost":"","rateType":"","programStatus":"1","signType":"","programType":"1","programImg":"","checkLabourerType":"","mpSign":"2","mpVacation":"2","formFeeRate":"0","programFeeRate":"0","feeRateStatus":"1","checkinStatus":"0","focusStatus":"0","locationxy":"29.594408003216344,106.56977977150018","mpSignRadius":"","remainingTime":297,"joinTime":0,"nodeInCount":0,"nodeOutCount":0,"checkinAllCount":0,"checkinCount":0,"checkinCountCompareYestoday":0,"reportCount":0,"imgCount":0},{"status":"1","createTime":1600247283820,"updateTime":1618391916864,"createBy":"200a1cf0d0764645addfd71f453b47ca","updateBy":"e2d85bdd87fe463ab7abf2aeaedf8e9e","id":"3adefc2b75e6498fb26741ec43543f1d","financialNumber":"G202002004","comId":"586238e5f33b4788b0d4663b18de33d4","partyaName":"长沙启顺房地产开发有限公司","programName":"长沙龙湖渔业路项目一期1组团户内及公区精装修工程","location":"14,197,1650","locationName":"湖南,长沙,开福区","address":"湖南省长沙市开福区福元西路","money":"63000000","startTime":"2020-12-15","endTime":"2022-03-15","programManagerId":"eb50f2ff8ef9414ea5e9875625ae7ab6","area":"9万方","totalBudgetCost":"","rateType":"","programStatus":"1","signType":"","programType":"1","programImg":"","checkLabourerType":"","mpSign":"2","mpVacation":"2","formFeeRate":"0","programFeeRate":"0","feeRateStatus":"1","checkinStatus":"0","focusStatus":"0","locationxy":"28.251915301132794,113.00368817945855","mpSignRadius":"","remainingTime":293,"joinTime":0,"nodeInCount":0,"nodeOutCount":0,"checkinAllCount":4,"checkinCount":0,"checkinCountCompareYestoday":0,"reportCount":0,"imgCount":0},{"status":"1","createTime":1602731819183,"updateTime":1615886604577,"createBy":"200a1cf0d0764645addfd71f453b47ca","updateBy":"de1b3f222e374b3baa71da8c17d99098","id":"401a072e7e394298b373115a753645be","financialNumber":"G202006010","comId":"586238e5f33b4788b0d4663b18de33d4","partyaName":"郑州盛德房地产开发有限公司","programName":"郑州万科世曦项目","location":"11,149,1251","locationName":"河南,郑州,金水区","address":"京广快速路与农业路交汇处","money":"28000000","startTime":"2020-10-18","endTime":"2021-12-31","programManagerId":"084ac8e72c2847f5aca27539e788b4bf","area":"80000m2","totalBudgetCost":"","rateType":"","programStatus":"1","signType":"","programType":"1","programImg":"","checkLabourerType":"","mpSign":"2","mpVacation":"2","formFeeRate":"5","programFeeRate":"0","feeRateStatus":"1","checkinStatus":"0","focusStatus":"0","locationxy":"34.78067993688453,113.63257553269705","mpSignRadius":"","remainingTime":219,"joinTime":0,"nodeInCount":0,"nodeOutCount":0,"checkinAllCount":0,"checkinCount":0,"checkinCountCompareYestoday":0,"reportCount":0,"imgCount":0},{"status":"1","createTime":1613877785033,"updateTime":1619403019350,"createBy":"2641e4119f714b928453c4d854a6fc58","updateBy":"e2d1b1b65c8c4a91a8ac49386715a31f","id":"3aa53128e1fe4327a667892dc258ec02","financialNumber":"G202103001","comId":"586238e5f33b4788b0d4663b18de33d4","partyaName":"南宁市龙光骏佳房地产开发有限公司","programName":"南宁龙光玖誉城悦城组团63#（商业）楼及地下室公共区域装修工程","location":"7,97,858","locationName":"广西,南宁,江南区","address":"南宁市白沙大道57号","money":"52177436.23","startTime":"2021-02-18","endTime":"2021-12-31","programManagerId":"1f28a8a518c94fa8983c0483fc6fe10e","area":"","totalBudgetCost":"","rateType":"","programStatus":"1","signType":"","programType":"3","programImg":"/ows-worker/deviceBehand/fd29a5128a054d09a58139fb86f9f9e2.jpg,/ows-worker/deviceBehand/b6bce04ea0ee4ee8a3e9cbda0145bede.png,/ows-worker/deviceBehand/24cf57a4cb6441b8a140bb0cee1ed149.png","checkLabourerType":"","mpSign":"2","mpVacation":"2","formFeeRate":"5","programFeeRate":"2","feeRateStatus":"1","checkinStatus":"0","focusStatus":"0","locationxy":"22.776179914505256,108.30379783057936","mpSignRadius":"","remainingTime":219,"joinTime":0,"nodeInCount":0,"nodeOutCount":0,"checkinAllCount":1,"checkinCount":0,"checkinCountCompareYestoday":0,"reportCount":0,"imgCount":0},{"status":"1","createTime":1595584637695,"updateTime":1620621146120,"createBy":"200a1cf0d0764645addfd71f453b47ca","updateBy":"e2d85bdd87fe463ab7abf2aeaedf8e9e","id":"13d3381ef38c4e7eb9457be048fe35cc","financialNumber":"G202002002","comId":"586238e5f33b4788b0d4663b18de33d4","partyaName":"长沙顺盛房地产开发有限公司","programName":"雨花经开区6号地块建设项目精装修工程","location":"14,197,1651","locationName":"湖南,长沙,雨花区","address":"长沙市雨花区同升街道桃阳村","money":"30000000","startTime":"2020-07-13","endTime":"2021-12-30","programManagerId":"2e0f87ec104844758618fcb3c2b4e091","area":"91759.18","totalBudgetCost":"","rateType":"0","programStatus":"1","signType":"","programType":"1","programImg":"","checkLabourerType":"","mpSign":"2","mpVacation":"2","formFeeRate":"0","programFeeRate":"0","feeRateStatus":"1","checkinStatus":"0","focusStatus":"0","locationxy":"28.086658727346215,113.04017212785425","mpSignRadius":"","remainingTime":218,"joinTime":0,"nodeInCount":0,"nodeOutCount":0,"checkinAllCount":1,"checkinCount":0,"checkinCountCompareYestoday":0,"reportCount":0,"imgCount":0},{"status":"1","createTime":1603947261387,"updateTime":1608773969735,"createBy":"200a1cf0d0764645addfd71f453b47ca","updateBy":"eb50f2ff8ef9414ea5e9875625ae7ab6","id":"46f899becea04e74adb9ab5c5cf985e7","financialNumber":"G202002006","comId":"586238e5f33b4788b0d4663b18de33d4","partyaName":"重庆兴冠寓商业管理有限公司","programName":"龙湖冠寓嘉陵大厦项目维修工程","location":"32,394,3340","locationName":"重庆,重庆,渝中区","address":"七星岗","money":"100000","startTime":"2020-10-10","endTime":"2021-11-30","programManagerId":"eb50f2ff8ef9414ea5e9875625ae7ab6","area":"18000","totalBudgetCost":"80000","rateType":"","programStatus":"1","signType":"","programType":"1","programImg":"","checkLabourerType":"","mpSign":"2","mpVacation":"2","formFeeRate":"0","programFeeRate":"0","feeRateStatus":"1","checkinStatus":"0","focusStatus":"0","locationxy":"29.565245,106.587639","mpSignRadius":"","remainingTime":188,"joinTime":0,"nodeInCount":0,"nodeOutCount":0,"checkinAllCount":0,"checkinCount":0,"checkinCountCompareYestoday":0,"reportCount":0,"imgCount":0},{"status":"1","createTime":1611298692589,"updateTime":1619403061273,"createBy":"2641e4119f714b928453c4d854a6fc58","updateBy":"e2d1b1b65c8c4a91a8ac49386715a31f","id":"cb8d06c09db5498ea9a34aa7da88dfdb","financialNumber":"G202100001","comId":"586238e5f33b4788b0d4663b18de33d4","partyaName":"西安金地浩泽房地产开发有限公司","programName":"金地集团西部区域西安天空之城项目一标段户内和公区精装修工程","location":"24,311,100168","locationName":"陕西,西安,曲江新区","address":"西安市西咸新区空港新城净业大街与立政路十字西南角","money":"20857691.98","startTime":"2021-02-26","endTime":"2021-09-30","programManagerId":"a38a7241e8a7495bb320d7ad24029c50","area":"40000","totalBudgetCost":"","rateType":"","programStatus":"1","signType":"1","programType":"1","programImg":"/ows-worker/deviceBehand/f3512631d9a7428da5068fc7d12fa704.jpg,/ows-worker/deviceBehand/1f28d20df71843f48b9a2bea7b7d121a.png,/ows-worker/deviceBehand/8dafd6064a2d4138a9c93f3e30ee3378.png","checkLabourerType":"","mpSign":"2","mpVacation":"2","formFeeRate":"5","programFeeRate":"2","feeRateStatus":"1","checkinStatus":"0","focusStatus":"0","locationxy":"34.416867620327956,108.79560687310095","mpSignRadius":"","remainingTime":127,"joinTime":0,"nodeInCount":0,"nodeOutCount":0,"checkinAllCount":0,"checkinCount":0,"checkinCountCompareYestoday":0,"reportCount":0,"imgCount":0},{"status":"1","createTime":1607333431723,"updateTime":1619402964494,"createBy":"2641e4119f714b928453c4d854a6fc58","updateBy":"e2d1b1b65c8c4a91a8ac49386715a31f","id":"4add540e8d18474886eae184fc3ea0be","financialNumber":"G202001002","comId":"586238e5f33b4788b0d4663b18de33d4","partyaName":"重庆招商置地开发有限公司","programName":"重庆市长嘉汇小区G3-4/02地块F2组团14号及15号楼户内批量精装修工程","location":"32,394,3329","locationName":"重庆,重庆,南岸区","address":"重庆南岸区弹子石","money":"47562753.05","startTime":"2020-12-07","endTime":"2021-09-30","programManagerId":"75ed79f93c7947258bd30a88ac1f4cf8","area":"","totalBudgetCost":"","rateType":"","programStatus":"1","signType":"","programType":"1","programImg":"/ows-worker/deviceBehand/9d25b7f119bf4539aadcc579a23395fd.png,/ows-worker/deviceBehand/53e498686410490c8db0da32a44bfca7.png,/ows-worker/deviceBehand/2670c9c67cdc417daf360b115920a23d.jpg","checkLabourerType":"","mpSign":"2","mpVacation":"2","formFeeRate":"5","programFeeRate":"2","feeRateStatus":"1","checkinStatus":"0","focusStatus":"0","locationxy":"29.57873932364344,106.58777251168226","mpSignRadius":"","remainingTime":127,"joinTime":0,"nodeInCount":0,"nodeOutCount":0,"checkinAllCount":0,"checkinCount":0,"checkinCountCompareYestoday":0,"reportCount":0,"imgCount":0},{"status":"1","createTime":1614238356184,"updateTime":1620621875207,"createBy":"2641e4119f714b928453c4d854a6fc58","updateBy":"f4d6430bd1a04e97b5683cf16e163ba2","id":"77cf838b988442d498ec344f2021e59a","financialNumber":"G202109001","comId":"586238e5f33b4788b0d4663b18de33d4","partyaName":"成都世茂新城房地产开发有限公司","programName":"龙泉世茂城五期住宅5号地块一期公区及户内精装修工程","location":"26,322,2727","locationName":"四川,成都,龙泉驿区","address":"四川省成都市龙泉驿区大面街道天鹅","money":"35637905","startTime":"2021-03-01","endTime":"2021-08-31","programManagerId":"d53e95189c0548829208132d2ebb8029","area":"60000","totalBudgetCost":"","rateType":"","programStatus":"1","signType":"","programType":"1","programImg":"/ows-worker/deviceBehand/11acd755bcef4972a0668622821f3ab7.png,/ows-worker/deviceBehand/de0c356407bb4dd6be54f664311596cd.png,/ows-worker/deviceBehand/904f3e75a76549efb472a7ef0481e980.jpg","checkLabourerType":"","mpSign":"2","mpVacation":"2","formFeeRate":"5","programFeeRate":"2","feeRateStatus":"1","checkinStatus":"0","focusStatus":"0","locationxy":"30.58438341183645,104.19447944121464","mpSignRadius":"","remainingTime":97,"joinTime":0,"nodeInCount":0,"nodeOutCount":0,"checkinAllCount":0,"checkinCount":0,"checkinCountCompareYestoday":0,"reportCount":0,"imgCount":0},{"status":"1","createTime":1562123659739,"updateTime":1616654300746,"createBy":"e2d1b1b65c8c4a91a8ac49386715a31f","updateBy":"de1b3f222e374b3baa71da8c17d99098","id":"7cc1ce44a47043b4ae9acab40db6a809","financialNumber":"G201906005","comId":"586238e5f33b4788b0d4663b18de33d4","partyaName":"河南民信置业有限公司","programName":"郑州万科江山府标准层电梯厅及室内精装修工程","location":"11,149,1257","locationName":"河南,郑州,惠济区","address":"郑州市惠济区国基路马金杯路交叉口向南200米","money":"25615009.94","startTime":"2019-07-15","endTime":"2021-08-30","programManagerId":"084ac8e72c2847f5aca27539e788b4bf","area":"","totalBudgetCost":"","rateType":"","programStatus":"1","signType":"1","programType":"1","programImg":"/ows-worker/deviceBehand/022ee46da4364870864deb0e296f7963.png,/ows-worker/deviceBehand/067f57be6de6431a972e2815a1408d14.png,/ows-worker/deviceBehand/feiyantixing.jpg","programColor":"#5999CC","checkLabourerType":"1","mpSign":"2","mpVacation":"2","formFeeRate":"5","programFeeRate":"2","feeRateStatus":"1","checkinStatus":"0","focusStatus":"0","locationxy":"34.819671508283115,113.63034143296898","mpSignRadius":"","remainingTime":96,"joinTime":0,"nodeInCount":0,"nodeOutCount":0,"checkinAllCount":0,"checkinCount":0,"checkinCountCompareYestoday":0,"reportCount":0,"imgCount":0}]
         */

        var totalNum: Int = 0
        var rows: List<RowsBean>? = null

        class RowsBean {
            /**
             * status : 1
             * createTime : 1615256285007
             * updateTime : 1620637154020
             * createBy : 2641e4119f714b928453c4d854a6fc58
             * updateBy : e2d85bdd87fe463ab7abf2aeaedf8e9e
             * id : 7541d6107ccb44b89fac86672b99d3dd
             * financialNumber : G202103003
             * comId : 586238e5f33b4788b0d4663b18de33d4
             * partyaName : 贤铭建设有限公司
             * programName : 武汉万科东风村项目K1地块精装修工程
             * location : 13,180,1547
             * locationName : 湖北,武汉,硚口区
             * address : 湖北省武汉市硚口区古田二路东风村
             * money : 25000000
             * startTime : 2021-03-15
             * endTime : 2022-12-31
             * programManagerId : d5f2b575bc8a4c2987e97c18b611ece7
             * area :
             * totalBudgetCost :
             * rateType :
             * programStatus : 1
             * signType :
             * programType : 1
             * programImg :
             * checkLabourerType :
             * mpSign : 2
             * mpVacation : 2
             * formFeeRate : 5
             * programFeeRate : 2
             * feeRateStatus : 1
             * checkinStatus : 0
             * focusStatus : 0
             * locationxy : 30.61067278396578,114.18556772891475
             * mpSignRadius :
             * remainingTime : 584
             * joinTime : 0
             * nodeInCount : 0
             * nodeOutCount : 0
             * checkinAllCount : 0
             * checkinCount : 0
             * checkinCountCompareYestoday : 0
             * reportCount : 0
             * imgCount : 0
             * programColor : #5999CC
             */

            var status: String? = null
            var createTime: Long = 0
            var updateTime: Long = 0
            var createBy: String? = null
            var updateBy: String? = null
            var id: String? = null
            var financialNumber: String? = null
            var comId: String? = null
            var partyaName: String? = null
            var programName: String? = null
            var location: String? = null
            var locationName: String? = null
            var address: String? = null
            var money: String? = null
            var startTime: String? = null
            var endTime: String? = null
            var programManagerId: String? = null
            var area: String? = null
            var totalBudgetCost: String? = null
            var rateType: String? = null
            var programStatus: String? = null
            var signType: String? = null
            var programType: String? = null
            var programImg: String? = null
            var checkLabourerType: String? = null
            var mpSign: String? = null
            var mpVacation: String? = null
            var formFeeRate: String? = null
            var programFeeRate: String? = null
            var feeRateStatus: String? = null
            var checkinStatus: String? = null
            var focusStatus: String? = null
            var locationxy: String? = null
            var mpSignRadius: String? = null
            var remainingTime: Int = 0
            var joinTime: Int = 0
            var nodeInCount: Int = 0
            var nodeOutCount: Int = 0
            var checkinAllCount: Int = 0
            var checkinCount: Int = 0
            var checkinCountCompareYestoday: Int = 0
            var reportCount: Int = 0
            var imgCount: Int = 0
            var programColor: String? = null
        }
    }
}
