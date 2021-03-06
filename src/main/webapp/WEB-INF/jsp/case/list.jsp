<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section id="main-content">
    <section class="wrapper">
        <h3><i class="fa fa-angle-right"></i> Basic Table Examples</h3>


        <div class="row mt">
            <div class="col-md-12">
                <div class="content-panel">
                    <table class="table table-striped table-advance table-hover">
                        <h4><i class="fa fa-angle-right"></i> Advanced Table</h4>
                        <thead>
                        <tr>
                            <th><i class="fa fa-bullhorn"></i> ID</th>
                            <th class="hidden-phone"><i class="fa fa-question-circle"></i> Name</th>
                            <th><i class="fa fa-bookmark"></i> Description</th>
                            <th><i class=" fa fa-edit"></i> Status</th>
                            <th>
                                <button class="case-add">添加用例</button>
                                <button class="upfile-btn">上传Excel</button>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${mcList}" var="mc">
                            <tr>
                                <td><a href="">${mc.caseId}</a></td>
                                <td>${mc.caseName}</td>
                                <td>${mc.caseDesc}</td>
                                <td>${mc.caseStatus}</td>
                                <td>
                                    <button class="btn btn-success btn-xs"><i class="fa fa-check"></i></button>
                                    <button class="btn btn-primary btn-xs btn-edit">
                                        <i class="fa fa-pencil"></i>
                                    </button>
                                    <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div><!-- /content-panel -->
            </div><!-- /col-md-12 -->
        </div><!-- /row -->

        <!-- 编辑用例及显示详情的模态框（Modal） -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
                    </div>
                    <div class="modal-body">
                        <form class="case-detail" id="caseForm">
                            <input type="text" name="caseId" id="caseId" style="display: none">
                            用例名称：<input type="text" name="caseName" id="caseName">
                            用例描述：<input type="text" name="caseDesc" id="caseDesc">
                            用例状态：<input type="text" name="caseStatus" id="caseStatus">
                            接口地址：<input type="text" name="dataUrl" id="dataUrl">
                            Header：<input type="text" name="dataHeader" id="dataHeader">
                            发送请求：<input type="text" name="dataSend" id="dataSend">
                            期望结果：<input type="text" name="dataExpect" id="dataExpect">
                            创建时间：<input type="text" name="createTime" id="createTime">
                            更新时间：<input type="text" name="updateTime" id="updateTime">
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary btn-update">提交</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>

        <!-- 编辑用例及显示详情的模态框（Modal） -->
        <div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="uploadModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="uploadModalLabel">模态框（Modal）标题</h4>
                    </div>
                    <form action="/case/upload" method="POST">
                        <div class="modal-body">
                            <label><a href="/case/downloadTemplate">点击这里</a>下载用例模板</label>
                            <label>选择文件：<input type="file" name="file"></label>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary js-upload-confirm">提交</button>
                        </div>
                    </form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>

    </section><! --/wrapper -->
</section><!-- /MAIN CONTENT -->
