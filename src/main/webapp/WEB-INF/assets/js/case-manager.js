$(function () {

    //点击详情和编辑按钮时，从后端获取该用例的详情
    $(".btn-edit").on("click",function(){
        var caseNode = $(this).parent().siblings().eq(0);
        var caseId = caseNode.text();//获取到caseId
        $.ajax({
            type:"POST",
            url:"detail",
            data:"caseId="+caseId,
            success:function (msg) {
                var obj = JSON.parse(msg);
                if(obj.code != 0){
                    alert(obj.msg)
                }
                var data = obj.data;
                $("#caseId").val(data.caseId);
                $("#caseName").val(data.caseName);
                $("#caseDesc").val(data.caseDesc);
                $("#caseStatus").val(data.caseStatus);
                $("#dataHeader").val(data.dataHeader);
                $("#dataSend").val(data.dataSend);
                $("#dataExpect").val(data.dataExpect);
                $("#createTime").val(data.createTime);
                $("#updateTime").val(data.updateTime);
            }
        })
        $("#myModal").modal('show');
    })

    //封装提交数据的方法
    function caseReplace(postUrl) {
        var caseData = $('#caseForm').serializeJSON();
        $.ajax({
            type:"POST",
            contentType:"application/json; charset=utf-8",
            url:postUrl,
            data: JSON.stringify(caseData),
            success:function (msg) {
                var obj = JSON.parse(msg);
                if(obj.code != 0){
                    alert(obj.msg)
                }
                console.log(obj)
            }
        })
        $("#myModal").modal('hide');
    }

    //点击提交更改时将内容更新到后台
    $(".btn-update").on("click",function(){
        var sufUrl;
        if($("#caseId").val()){
            sufUrl = "update";
        }else{
            sufUrl = "add";
        }
        caseReplace(sufUrl);
    });

    //绑定添加用例按钮的点击事件
    $(".case-add").on("click",function(){
        $("#myModal").modal('show');
    })
})