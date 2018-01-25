<%@page contentType="text/html; charset=UTF-8" %>
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
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${mcList}" var="mc">
                            <tr>
                                <td><a href="">${mc.caseId}</a></td>
                                <td class="hidden-phone">${mc.caseName}</td>
                                <td>${mc.caseDesc}</td>
                                <td>${mc.caseStatus}</td>
                                <td>
                                    <button class="btn btn-success btn-xs"><i class="fa fa-check"></i></button>
                                    <button class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button>
                                    <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div><!-- /content-panel -->
            </div><!-- /col-md-12 -->
        </div><!-- /row -->

    </section><! --/wrapper -->
</section><!-- /MAIN CONTENT -->
