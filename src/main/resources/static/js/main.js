$(function(){
    var PATTERN_CHECKBOX_ID = '[id^="check_"]'
    var EVENT_CHANGE = 'change'

    $(PATTERN_CHECKBOX_ID).each(function () {
        toggleEditMode(this)
    })

    $('#checkAll').on(EVENT_CHANGE, function() {
        if (this.checked) {
            toggleCheckBox(PATTERN_CHECKBOX_ID, true)
        } else {
            toggleCheckBox(PATTERN_CHECKBOX_ID, false)
        }
    })

    $(PATTERN_CHECKBOX_ID).on(EVENT_CHANGE, function() {
        toggleEditMode(this)
    })

    $('#deleteCheck').on('click', function () {
        if ($('[id^="check_"]:checked').length === 0) {
            alert('You must choice some data.')
            return false
        }
        return true
    })

})

function toggleCheckBox (id, flag) {
    $(id).prop('checked', flag).change()
}

function toggleEditMode (elem) {
    var checked = $(elem).prop('checked')
    var tr = $(elem).closest('tr')
    $(tr).find('[type="text"],select').prop('disabled', !checked)
}
