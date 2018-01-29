$(function () {

    //点击详情和编辑按钮时，从后端获取该用例的详情
    $(".btn-primary").on("click",function(){
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

})