<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>DASHGUM - Bootstrap Admin Template</title>

    <%@include file="../../jspf/css.jspf"%>

</head>

<body>

<section id="container" >
    <%@include file="../_header.jsp"%>
    <%@include file="../_sidebar.jsp"%>
    <%@include file="list.jsp"%>

    <%--结果页面正式内容开始--%>
    <section id="main-content">
        <section class="wrapper">
            <h3><i class="fa fa-angle-right"></i> Basic Table Examples</h3>


            <div class="row mt">
                <div class="col-md-12">
                    <div class="content-panel">
                        <table class="table table-striped table-advance table-hover">
                            <thead>
                            <tr>
                                <th>导入文件</th>
                                <th>导入结果</th>
                                <th>操作人</th>
                                <th>操作时间</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div><!-- /content-panel -->
                </div><!-- /col-md-12 -->
            </div><!-- /row -->

        </section><! --/wrapper -->
    </section><!-- /MAIN CONTENT -->


    <%@include file="../_footer.jsp"%>
</section>

<%@include file="../../jspf/js.jspf"%>

<!--script for this page-->
<script src="../../assets/js/jquery.serializejson.js"></script>

</body>
</html>
