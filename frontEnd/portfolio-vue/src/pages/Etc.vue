<script setup>
import { ref, computed } from 'vue'

const SIZE = 15                        // 보드 크기 (원하면 19로)
const board = ref(Array.from({ length: SIZE }, () => Array(SIZE).fill(0))) // 0=빈칸, 1=흑, 2=백
const turn = ref(1)
const winner = ref(0)
const lastMove = ref(null)             // { r, c }
const history = ref([])                // 스택: { r, c, player }
const winningLine = ref([])            // [[r,c],...]

// 안내 문구 (6목 자동 취소 등)
const notice = ref('')
let noticeTimer
const showNotice = (msg, ms = 2600) => {
  clearTimeout(noticeTimer)
  notice.value = msg
  noticeTimer = setTimeout(() => (notice.value = ''), ms)
}

const currentLabel = computed(() =>
  winner.value
    ? (winner.value === 1 ? '흑(Black) 승리!' : '백(White) 승리!')
    : (turn.value === 1 ? '흑(Black) 차례'   : '백(White) 차례')
)

const isWinCell = (r, c) => winningLine.value.some(([rr, cc]) => rr === r && cc === c)
const inBounds = (r, c) => r >= 0 && r < SIZE && c >= 0 && c < SIZE

// ✅ 한 방향(양쪽)으로 연속 개수와 라인 반환
function lineCount(r, c, dr, dc, player) {
  let count = 1
  const line = [[r, c]]
  let rr = r + dr, cc = c + dc
  while (inBounds(rr, cc) && board.value[rr][cc] === player) {
    line.push([rr, cc]); count++; rr += dr; cc += dc
  }
  rr = r - dr; cc = c - dc
  while (inBounds(rr, cc) && board.value[rr][cc] === player) {
    line.unshift([rr, cc]); count++; rr -= dr; cc -= dc
  }
  return { count, line }
}

// ✅ “정확히 5목” 판정: 5면 승리, 6↑면 overline
function checkExactFive(r, c, player) {
  const dirs = [[1,0],[0,1],[1,1],[1,-1]]
  for (const [dr, dc] of dirs) {
    const { count, line } = lineCount(r, c, dr, dc, player)
    if (count > 5)   return { type: 'overline' }
    if (count === 5) return { type: 'win', line }
  }
  return { type: 'none' }
}

// ▶ 둔다
const place = (r, c) => {
  if (winner.value || board.value[r][c] !== 0) return
  board.value[r][c] = turn.value
  lastMove.value = { r, c }
  history.value.push({ r, c, player: turn.value })

  const res = checkExactFive(r, c, turn.value)

  if (res.type === 'win') {
    winningLine.value = res.line
    winner.value = turn.value
    return
  }

  if (res.type === 'overline') {
    // 6목 이상 → 자동 되돌리기 + 안내
    undo()
    showNotice('6목 이상은 불가능합니다. (정확히 5목만 인정)')
    return
  }

  // 정상 진행
  turn.value = 3 - turn.value
}

// ▶ 되돌리기
const undo = () => {
  if (!history.value.length) return
  const last = history.value.pop()
  board.value[last.r][last.c] = 0
  winner.value = 0
  winningLine.value = []
  lastMove.value = history.value.length ? history.value[history.value.length - 1] : null
  turn.value = last.player  // 무효수 되돌릴 때도 같은 사람 차례 유지
}

// ▶ 새로 시작
const reset = () => {
  board.value = Array.from({ length: SIZE }, () => Array(SIZE).fill(0))
  turn.value = 1; winner.value = 0; winningLine.value = []
  lastMove.value = null; history.value = []; notice.value = ''
}
</script>

<template>
  <section class="py-4">
    <h2 class="h4 mb-3">오목</h2>

    <div class="d-flex align-items-center gap-2 mb-3">
      <span class="badge text-bg-secondary">{{ SIZE }}×{{ SIZE }}</span>
      <span class="fw-semibold" :class="winner ? 'text-success' : 'text-primary'">{{ currentLabel }}</span>
      <div class="ms-auto d-flex gap-2">
        <button class="btn btn-outline-secondary btn-sm" @click="undo" :disabled="!history.length || winner">Undo</button>
        <button class="btn btn-primary btn-sm" @click="reset">Restart</button>
      </div>
    </div>
    <div v-if="notice" class="alert alert-danger py-2 px-3 small mb-3">
      {{ notice }}
    </div>
    <!-- 보드 -->
    <div class="omok-wrap">
        <div class="omok-board" :style="{ '--size': SIZE }">
          <!-- r 반복을 template로 감싸고, 실제 버튼엔 c 반복만 남깁니다 -->
          <template v-for="r in SIZE" :key="'r'+r">
            <button
              v-for="c in SIZE"
              :key="`cell-${r}-${c}`"
              class="omok-cell"
              @click="place(r-1, c-1)"
              :aria-label="`r${r}-c${c}`"
            >
              <span
                v-if="board[r-1][c-1] !== 0"
                class="stone"
                :class="{
                  black: board[r-1][c-1] === 1,
                  white: board[r-1][c-1] === 2,
                  last:  lastMove && lastMove.r === (r-1) && lastMove.c === (c-1),
                  win:   isWinCell(r-1, c-1)
                }"
              />
            </button>
          </template>
        </div>
    </div>

    <p class="text-muted small mt-3">  규칙: 가로/세로/대각선으로 <strong>정확히 다섯 개</strong>를 잇는 사람이 승리합니다.
    <strong>6목 이상은 금지</strong>이며, 두면 자동으로 되돌려집니다.</p>
  </section>
</template>

<style scoped>
/* 보드 외곽 & 배경 */
.omok-wrap{ display:flex; justify-content:center; }
.omok-board{
  --cell: 34px;                   /* 한 칸 크기 */
  position: relative;
  width: calc(var(--cell) * var(--size) + 24px);
  height: calc(var(--cell) * var(--size) + 24px);
  padding: 12px;
  background: radial-gradient(ellipse at center, #e9c78f, #e0b873);
  border-radius: 14px;
  box-shadow: 0 8px 24px rgba(0,0,0,.15);
  display: grid;
  grid-template-columns: repeat(var(--size), var(--cell));
  grid-template-rows: repeat(var(--size), var(--cell));
}

/* 격자선: 안쪽에만 그리드 */
.omok-board::after{
  content: ""; position:absolute; inset:12px; pointer-events:none;
  border: 1.5px solid rgba(0,0,0,.55);
  background-image:
    repeating-linear-gradient(to right, rgba(0,0,0,.35) 0 1px, transparent 1px calc(var(--cell))),
    repeating-linear-gradient(to bottom, rgba(0,0,0,.35) 0 1px, transparent 1px calc(var(--cell)));
  border-radius: 6px;
}

/* 칸(클릭 영역) */
.omok-cell{
  background: transparent; border: 0; padding: 0; margin: 0;
  display: grid; place-items: center; cursor: pointer;
}
.omok-cell:focus{ outline: 2px solid rgba(99,102,241,.3); }

/* 돌 */
.stone{
  position:relative;
  width: 72%; height: 72%; border-radius: 50%;
  box-shadow: inset 0 1px 3px rgba(255,255,255,.4), inset 0 -2px 6px rgba(0,0,0,.35), 0 2px 6px rgba(0,0,0,.2);
}
.stone.black{ background: radial-gradient(circle at 30% 30%, #444, #111 65%); }
.stone.white{ background: radial-gradient(circle at 30% 30%, #fff, #cfcfcf 70%); border: 1px solid rgba(0,0,0,.2); }

/* 마지막 수 표시(작은 점) */
.stone.last::after{
  content:"";
  position: absolute;
  width: 18%;
  height:18%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius:50%;
  background: rgba(0,0,0,.7);
  pointer-events: none;
}
/* 흑돌이면 → 흰 점 (얇은 외곽선으로 가독성 ↑) */
.stone.black.last::after{
  background: #fff;
  box-shadow: 0 0 0 1px rgba(0,0,0,.3);
}

/* 백돌이면 → 검은 점 (흰 배경에서도 보이게 약간의 밝기 테두리) */
.stone.white.last::after{
  background: #000;
  box-shadow: 0 0 0 1px rgba(255,255,255,.35);
}

/* 승리 라인 하이라이트(테두리) */
.stone.win{ outline: 3px solid #22c55e; outline-offset: 2px; }

/* 반응형: 좁은 화면에서는 칸을 살짝 줄이기 */
@media (max-width: 480px){
  .omok-board{ --cell: 26px; }
}
</style>
