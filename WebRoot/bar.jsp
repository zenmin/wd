<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="head.jsp"></jsp:include>
<script type="text/javascript" src="js/showBar.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        showBar();
    });

    function jumpPage(value) {
        var currentPage = 1;
        currentPage = parseInt($("#currentPage").text());//当前页数
        pageSize = parseInt($("#pageSize").text());//总共页数
        arrivePage = $("#arrivePage").val();//目标页数
        console.debug("当前页数：" + currentPage + "  总共页数：" + pageSize + "  目标页数："
                + arrivePage);

        var isNum = /^[0-9]+$/g;

        //      判断是上一页还是下一页
        if (value == "上一页") {
            //判断是否第一页
            if (currentPage <= 1) {
                alert("这已经是第一页了，没有上一页了！");
                return false;
            }
            currentPage--;//页数减一
            arrivePage = currentPage;//赋值给目标页面
        } else if (value == "翻页") {
            if (arrivePage >= 1 && arrivePage <= pageSize
                    && isNum.test(arrivePage)) {
                arrivePage = parseInt(arrivePage);
            } else {
                alert("请输入正确的页数");
                $("#arrivePage").val(currentPage);
                return false;
            }
            currentPage = arrivePage;
        } else {
            //          判断是否是最后一页
            if (currentPage >= pageSize) {
                alert("这已经是最后一页了，没有下一页了！");
                return false;
            }
            currentPage++;//页数加一
            arrivePage = currentPage;//赋值给目标页面
        }
        $("#currentPage").text(currentPage);//把改变的当前页数赋值给页面的当前页面
        $("#arrivePage").val(arrivePage);
        $("#btn-submit").click();
    }
    //      重新选择条件后，则初始化目标页数为1
    function initArrivePage() {
        $("#currentPage").text(1);
        $("#arrivePage").val(1);
    }

    //验证输入是否出错
    function validateContitionValue() {
        initArrivePage();
        var str = null;
        var contitionIndex = document.getElementById("contition").selectedIndex;
        var contitionValue = document.getElementById("contitionValue").value;
        switch (contitionIndex) {
        case 1:
            str = /^[a-zA-Z0-9]$/;//    不能输入中文
            if (contitionValue != "" || str.test(contitionValue)) {
                return true;
            } else {
                alert("请输入正确的货号！");
                return false;
            }
            break;
        case 2:
            str = /^[0-9]$/;//  只能输入数字
            if (contitionValue != "" || str.test(contitionValue)) {
                return true;
            } else {
                alert("请输入正确的条码！");
                return false;
            }
            break;
        case 3:
            if (contitionValue != "")
                return true;
            return false;
        }

    }
</script>


<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main"   >
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
            <li class="active">产品资料</li>
        </ol>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">产品资料</h1>
        </div>

        &nbsp;&nbsp;&nbsp;通过 货号 / 条码 / 简称 / 类目 查询，默认为全部(可选小组)：
        <s:select id="contition" name="contition"
            list="#{'0':'全部','1':'货号','2':'条码','3':'简称','4':'类目'}"
            onchange="selectChange(this.selectedIndex)" style="width:80px;">
        </s:select>
        &nbsp; <span id="ownerLabel">小组：<s:select id="owner"
                name="owner" list="%{request.mapOwners }"
                onchange="initArrivePage()" listKey="key" listValue="value" />
        </span> &nbsp;
         &nbsp; <span id="classLabel"  style="display:none;">类目：<s:select id="classS"
                name="classS" list="%{request.mapClass }"
                onchange="initArrivePage()" listKey="key" listValue="value"
                 />
        </span> &nbsp;

        <s:textfield id="contitionValue" name="contitionValue" size="16"
            placeholder="无需填写条件" onkeyup="validateContitionValue()"
            readonly="readonly" style="display:none;width:200px;"></s:textfield>
        &nbsp;&nbsp; <input type="submit" class="btn btn-primary btn-md"
            id="btn-submit" onclick="showBar()" value="提交查询" />
              <span id="msg1" style="color:red;float:right;">*注意：按左右方向键可以左右滚动屏幕。显示"undefined"表示记录为空！</span>  
        <div id="main">
                <table class="zebra" style="text-align: center;"  border=1 bordercolor="#ccc">
                <thead>
                   <tr width="150px">
                        <th style="text-align: center;">小&nbsp;&nbsp;组</th>
                        <th style="text-align: center;">条&nbsp;&nbsp;码</th>
                        <th style="text-align: center;">&nbsp;&nbsp;货&nbsp;&nbsp;号&nbsp;&nbsp;</th>
                        <th style="text-align: center;">简&nbsp;&nbsp;称</th>
                        <th style="text-align: center;">别&nbsp;&nbsp;名</th>
                        <th style="text-align: center;">成&nbsp;&nbsp;本</th>
                        <th style="text-align: center;">吊&nbsp;&nbsp;牌&nbsp;&nbsp;价</th>
                        <th style="text-align: center;">售&nbsp;&nbsp;价</th>
                        <th style="text-align: center;">颜&nbsp;&nbsp;色</th>
                        <th style="text-align: center;">&nbsp;&nbsp;材&nbsp;&nbsp;质&nbsp;&nbsp;</th>
                        <th style="text-align: center;">规&nbsp;&nbsp;格</th>
                        <th style="text-align: center;">&nbsp;&nbsp;类&nbsp;&nbsp;&nbsp;&nbsp;目&nbsp;&nbsp;</th>
                        <th style="text-align: center;">执&nbsp;行&nbsp;标&nbsp;准</th>
                        <th style="text-align: center;">&nbsp;&nbsp;备&nbsp;&nbsp;注&nbsp;&nbsp;</th>
                        <th style="text-align: center;">重&nbsp;&nbsp;量</th>
                        <th style="text-align: center;">长&nbsp;&nbsp;</th>
                        <th style="text-align: center;">宽&nbsp;&nbsp;</th>
                        <th style="text-align: center;">高&nbsp;&nbsp;</th>
                        <th style="text-align: center;">是&nbsp;否&nbsp;易&nbsp;损</th>
                        <th style="text-align: center;">说&nbsp;明&nbsp;书</th>
                        <th style="text-align: center;">包&nbsp;装&nbsp;货&nbsp;号</th>
                        <th style="text-align: center;">包&nbsp;装&nbsp;情&nbsp;况</th>
                        <th style="text-align: center;">包&nbsp;装&nbsp;尺&nbsp;寸</th>
                        <th style="text-align: center;">大小件区分</th>
                        <th style="text-align: center;">标&nbsp;&nbsp;记</th>
                        <th style="text-align: center;">保质期</th>
                        <s:if test="#session.power == 1">
                        <th style="text-align: center;">操&nbsp;&nbsp;作</th>
                        </s:if>
                    </tr>
                </thead>
                <tbody id="barTable">
                    <tr>
                        <td align="center" id="msg" style="line-height: 5;color:blue;"
                            colspan="26"><span class="msg">没有查询结果！</span></td>
                    </tr>
                </tbody>
                <tfoot id="pageTable">
                    <tr>
                        <td colspan="26" rowspan="1" align="center"><a><input
                                type="button" onclick="jumpPage(this.value)" id="previousPage"
                                value="上一页" /></a> 当前第 <span id="currentPage">1</span> 页 <a><input
                                type="button" onclick="jumpPage(this.value)" id="nextPage"
                                value="下一页" /></a> <br>总 <span id="pageSize"></span> 页 翻页:<a><input
                                type="text" id="arrivePage" name="arrivePage" value="1" size="1" />
                                <input type="button" onclick="jumpPage(this.value)" id="jumpBtu"
                                value="翻页" /></a></td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>

</div>

<jsp:include page="foot.jsp"></jsp:include>