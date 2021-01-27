import axios from 'axios'

const client = axios.create({
  baseURL: 'http://localhost:8080',
  json: true
})

export default {
  async execute (method, endpoint, data) {
    return client({
      method,
      url: endpoint,
      data,
      headers: {}
    }).then(req => {
      return req.data
    })
  },
  findAll () {
    return this.execute('GET', '/stocks')
  },
  findById (id) {
    return this.execute('GET', `/stocks/${id}`)
  },
  save (data) {
    return this.execute('POST', '/stocks', data)
  },
  update (id, data) {
    return this.execute('PUT', `/stocks/${id}`, data)
  }
}
