$(function(){
    $('#checkAll').on('change', function() {
        if (this.checked) {
            toggleCheckBox('[id^=check_', true)
        } else {
            toggleCheckBox('[id^=check_', false)
        }
    });
});

function toggleCheckBox(id, flag) {
    $(id).prop('checked', flag)
}
