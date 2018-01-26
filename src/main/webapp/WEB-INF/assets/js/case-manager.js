$(function () {
    function editInfo(obj) {
        var tds= $(obj).parent().siblings();
        $(".modal-body").val(tds.eq(0).text());
        $('#modal').modal('show');
    }
})