const codes = document.querySelectorAll('.code')

codes[0].focus()

codes.forEach((code, idx) => {
    code.addEventListener('keyup', (e) => {
        if(e.keyCode >= 48 && e.keyCode <= 57) {
            setTimeout(() => codes[idx + 1].focus(), 10)
        } else if(e.key === 'Backspace') {
            codes[idx].value = ''
            setTimeout(() => codes[idx - 1].focus(), 10)
        } else if(e.key === 'Enter'){} 
        else{
            codes[idx].value = ''
            e.preventDefault()
        }
    })
})