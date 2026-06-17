const canvas = document.getElementById("game");
const ctx = canvas.getContext("2d");

const stageText = document.getElementById("stage");
const scoreText = document.getElementById("score");
let currentTargetImage = 0;
const targetPreview =
    document.getElementById(
        "targetPreview"
    );
const images = [];

for(let i = 44; i <= 47; i++){
    const img = new Image();
    img.src =  `イラスト${i}.png`;
    images.push(img);
}
let people = [];

let stage = 1;
let score = 0;
let stageType = "grid";
const WIDTH = canvas.width;
const HEIGHT = canvas.height;

class Person {

    constructor(x, y, imageIndex,target = false) {

        this.x = x;
        this.y = y;

        this.tx = x;
        this.ty = y;
        this.imageIndex = imageIndex;
        this.target = target;
        
        this.radius = target ? 20 : 18;

        this.vx = (Math.random()-0.5)*2;
        this.vy = (Math.random()-0.5)*2;

        this.groupVX = 0;
        this.groupVY = 0;

        this.moveTarget = false;
    }

    update() {

        this.x += (this.tx - this.x) * 0.05;
        this.y += (this.ty - this.y) * 0.05;
        if(stageType === "moving"){
            this.x += this.vx;
            this.y += this.vy;

            if(this.x < 20 || this.x > WIDTH-20)
                this.vx *= -1;

            if(this.y < 20 || this.y > HEIGHT-20)
                this.vy *= -1;
        }

        if(stageType === "crowd"){

            this.x += this.groupVX;
            this.y += this.groupVY;

            if(this.x > WIDTH + 30)
                this.x = -30;

            if(this.x < -30)
                this.x = WIDTH + 30;

            if(this.y > HEIGHT + 30)
                this.y = -30;

            if(this.y < -30)
                this.y = HEIGHT + 30;
        }

        if(this.moveTarget){

            this.x += this.vx * 1.5;
            this.y += this.vy * 1.5;

            if(this.x < 20 || this.x > WIDTH-20)
                this.vx *= -1;

            if(this.y < 20 || this.y > HEIGHT-20)
                this.vy *= -1;
        }
    }

    draw() {

        const img = images[this.imageIndex];

        ctx.drawImage(
            img,
            this.x - this.radius,
            this.y - this.radius,
            this.radius * 2,
            this.radius * 2
        );
    }
}

function distance(x1, y1, x2, y2) {

    const dx = x1 - x2;
    const dy = y1 - y2;

    return Math.sqrt(dx * dx + dy * dy);
}

function validPosition(x, y, minDist) {

    for (const p of people) {

        if (
            distance(
                x,
                y,
                p.x,
                p.y
            ) < minDist
        ) {
            return false;
        }
    }

    return true;
}

function randomPosition(minDist) {

    let x;
    let y;

    do {

        x =
            Math.random() * (WIDTH - 100) + 50;

        y =
            Math.random() * (HEIGHT - 100) + 50;

    } while (
        !validPosition(
            x,
            y,
            minDist
        )
    );

    return { x, y };
}

function createGrid(count) {

    stageType = "grid";
    people = [];

    const cols =
        Math.ceil(Math.sqrt(count));

    const spacing = 50;

    const targetIndex =
        Math.floor(
            Math.random() * count
        );

    currentTargetImage =
        Math.floor(
            Math.random() *
            images.length
        );

    for (let i = 0; i < count; i++) {

        const x =
            80 +
            (i % cols) * spacing;

        const y =
            80 +
            Math.floor(i / cols) *
            spacing;

        let imageIndex;

        if(i === targetIndex){

            imageIndex =
                currentTargetImage;

        }else{

            do{

                imageIndex =
                    Math.floor(
                        Math.random() *
                        images.length
                    );

            }while(
                imageIndex === currentTargetImage
            );
        }

        people.push(
            new Person(
                x,
                y,
                imageIndex,
                i === targetIndex
            )
        );
    }
}

function createRandom(count){

    stageType = "random";
    people = [];

    currentTargetImage =
        Math.floor(
            Math.random() *
            images.length
        );

    const targetIndex =
        Math.floor(
            Math.random() * count
        );

    for(let i = 0; i < count; i++){

        const pos =
            randomPosition(35);

        let imageIndex;

        if(i === targetIndex){

            imageIndex =
                currentTargetImage;

        }else{

            do{

                imageIndex =
                    Math.floor(
                        Math.random() *
                        images.length
                    );

            }while(
                imageIndex === currentTargetImage
            );
        }

        people.push(
            new Person(
                pos.x,
                pos.y,
                imageIndex,
                i === targetIndex
            )
        );
    }
}
function createMoving(count){
    createRandom(count);
    stageType = "moving";
}

function createShuffle(count) {
    stageType = "shuffle";
    createGrid(count);

    setTimeout(() => {

        const old = [...people];

        people = [];

        for (const p of old) {

            const pos =
                randomPosition(42);

            p.tx = pos.x;
            p.ty = pos.y;

            people.push(p);
        }

    }, 1000);
}
function createCrowd(count){

    createRandom(count);

    stageType = "crowd";

    const directions = [
        [2,0],
        [-2,0],
        [0,2],
        [0,-2]
    ];

    const dir =
        directions[
            Math.floor(
                Math.random()*4
            )
        ];

    for(const p of people){

        p.groupVX = dir[0];
        p.groupVY = dir[1];
    }
}
function createMovingTarget(count){

    createRandom(count);

    stageType = "movingTarget";

    const target =
        people.find(
            p => p.target
        );

    if(target){
        target.moveTarget = true;
    }
}

function createStage() {
    const cycleStage =
        ((stage - 1) % 5) + 1;

    const count =
        50 + cycleStage * 15;

    switch(stage % 6){

        case 1:
            createGrid(count);
            break;

        case 2:
            createRandom(count);
            break;

        case 3:
            createMoving(count);
            break;

        case 4:
            createShuffle(count);
            break;

        case 5:
            createCrowd(count);
            break;

        case 0:
            createMovingTarget(count);
            break;
    }
    stageText.textContent = stage;
    targetPreview.src =
        images[
            currentTargetImage
        ].src;
}

function draw() {

    ctx.clearRect(
        0,
        0,
        WIDTH,
        HEIGHT
    );

    for (const p of people) {
        p.draw();
    }
}

function update() {

    for (const p of people) {
        p.update();
    }
}

canvas.addEventListener(
    "click",
    e => {

        const rect =
            canvas.getBoundingClientRect();

        const mx =
            e.clientX - rect.left;

        const my =
            e.clientY - rect.top;

        for (
            let i =
                people.length - 1;
            i >= 0;
            i--
        ) {

            const p = people[i];

            const d =
                distance(
                    mx,
                    my,
                    p.x,
                    p.y
                );

            if (d <= p.radius) {
                if (p.target) {

                    score +=
                        stage * 100;

                    stage++;

                    scoreText.textContent =
                        score;

                    createStage();

                } else {

                    score =
                        Math.max(
                            0,
                            score - 20
                        );

                    scoreText.textContent =
                        score;
                }

                break;
            }
        }
    }
);

function loop() {

    update();
    draw();

    requestAnimationFrame(
        loop
    );
}

createStage();
loop();