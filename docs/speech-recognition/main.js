import { Config, AiCode } from './config.js'
import Recorder from './Recorder.js'
console.log('语音识别核心文件')
let ctime = 0
let current = 0
let recording = false
let result = [] 
let _realTimeWrite = null
const langArr = ['cn', 'sichuanese', 'cantonese', 'en']
let interalHandler = null
let recorder = null
let ws = null
const { appKey, secret, path, sign } = Config[AiCode.One]
console.log('配置文件', Config)

export function startRecording() {
  recording = true
  interalHandler = setInterval(() => {
    ctime = ctime + 1
    if (ctime > 60) {
      endRecording()
    }
  }, 1000)
  doRecording()
}
export function endRecording() {
  clearInterval(interalHandler)
  recording = false
  ctime = 0
  stopRecording()
}
export function getResult() {
  return result
}
// export function setrealTimeWrite(fn){
//   realTimeWrite = fn
// }
export function realTimeWrite(fn){
  _realTimeWrite = fn
}
function doRecording() {
  recorder = new Recorder(onaudioprocess)
  console.log(recorder)
  createWS()
  recorder.ready().then(
    () => {
      recorder.start()
    },
    () => {
      console.warn('录音启动失败！')
      if (ws) ws.close()
    }
  )
}
function stopRecording() {
  if (!recorder) return
  recorder.stop()
  if (ws && ws.readyState === 1) {
    ws.send(
      JSON.stringify({
        type: 'end'
      })
    )
  }
}
function createWS() {
  const tm = 1663752410928 || +new Date()
  // const sign = sha256(`${appKey}${tm}${secret}`).toUpperCase();
  // console.log('sign',sign)
  ws = new WebSocket(`${path}?appkey=${appKey}&time=${tm}&sign=${sign}`)
  ws.onopen = () => {
    ws.send(
      JSON.stringify({
        type: 'start',
        sha: '256',
        data: {
          acoustic_setting: 'near',
          domain: 'general',
          format: 'pcm',
          lang: 'cn',
          max_end_silence: '500',
          max_start_silence: '1000',
          post_proc: 'true',
          punctuation: 'true',
          sample: '16k',
          server_vad: 'false',
          userid: 'userid-001'
        }
      })
    )

    // ws.close();
  }
  let msgArr = []
  ws.onmessage = evt => {
    const res = JSON.parse(evt.data)
    if (res.code == 0 && res.text) {
      if (msgArr.length > 0 && msgArr[msgArr.length - 1].type !== 'fixed') {
        msgArr[msgArr.length - 1] = res
      } else {
        msgArr.push(res)
      }
      result = [...msgArr]
      _realTimeWrite(result)
      // setResult([...msgArr]);
    }
  }

  ws.onclose = e => {
    ws.close()
    msgArr = []
    ws = null
  }
}
function onaudioprocess(buffer) {
  if (ws && ws.readyState === 1) {
    ws.send(buffer)
  }
}
