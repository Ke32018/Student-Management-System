<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>
        学生信息管理平台
    </title>
    <link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css"/>
    <link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css"/>
    <link href="../../Style/ks.css" rel="stylesheet" type="text/css"/>
    <link href="../../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../../Script/Common.js" type="text/javascript"></script>
    <script src="../../Script/Data.js" type="text/javascript"></script>


</head>
<body>

<div class="div_head">
            <span>
                <span style="float:left">当前位置是：教务中心-》考试-》新增</span>
                <span style="float:right;margin-right: 8px;font-weight: bold">
                    <a style="text-decoration: none" href="javascript:history.back();">【返回】</a>
                </span>
            </span>
</div>
</div>
<div class="cztable">
    <form action="roles?method=insert" method="post">
        <table border="1" width="100%" class="table_a">
            <tr width="120px;">
                <td width="120px">角色名：<span style="color:red">*</span>：</td>
                <td>
                    <input type="text" name="roleName" value="管理员"/>
                </td>
            </tr>

            <tr width="120px;">
                <td>菜单资源<span style="color:red">*</span>：</td>
                <td>
                    <c:forEach items="${menuList}" var="m">
                        <ul>
                            <li><input type="checkbox" value="${m.menuId}" name="menuid"/>${m.menuName}
                                <ul>
                                    <c:forEach items="${m.secondMenuList}" var="m2">
                                        <li>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" value="${m2.menuId}"
                                                                           name="menuid"/>${m2.menuName}</li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </ul>
                    </c:forEach>
                </td>
            </tr>

            <tr>
                <td>启用状态<span style="color:red">*</span>：</td>
                <td>
                    <input type="radio" name="state" checked value="1"/>启用 <input type="radio" name="state" value="0"/>禁用
                </td>
            </tr>

            <tr width="120px">
                <td colspan="2" align="center">
                    <input type="submit" value="添加"/>
                </td>
            </tr>
        </table>
    </form>
</div>

</div>
</div>

</div>
</body>
</html>
