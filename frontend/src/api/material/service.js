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
    return this.execute('GET', '/materials')
  },
  findById (id) {
    return this.execute('GET', `/materials/${id}`)
  },
  save (data) {
    return this.execute('POST', '/materials', data)
  },
  update (id, data) {
    return this.execute('PUT', `/materials/${id}`, data)
  },
  delete (id) {
    return this.execute('DELETE', `/materials/${id}`)
  }
}
