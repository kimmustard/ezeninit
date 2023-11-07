let pageNum = 0;
let mainCnt = 0;
const mainSlider = document.getElementById('boardContainer');


document.getElementById('rightBtn').addEventListener('click', () => {


    if (mainCnt == 1398) {
        mainCnt = 1398;
    } else {
        mainCnt += 466;
    }

    mainSlider.style.transition = '1s';
    mainSlider.style.marginLeft = `-${mainCnt}px`;
})

document.getElementById('leftBtn').addEventListener('click', () => {

    if (mainCnt == 0) {
        mainCnt = 0;
    } else {
        mainCnt -= 466;
    }

    mainSlider.style.transition = '1s';
    mainSlider.style.marginLeft = `-${mainCnt}px`;

})

