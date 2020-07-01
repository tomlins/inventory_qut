# inventory_qut
Spring Boot REST application for QUT


Sample Spring Boot RESTfull application based on question posed by QUT.
The application is an implementation of the following API

```
swagger: '2.0'
info:
  title: Simple Inventory API
  description: |
    This test is designed to demonstrate real world problem solving skills.
    The task is to build a simple Spring Boot application and submit it for review via a git repository.

    It should include:
    - Spring MVC REST controller with mock mvc test
    - JPA mapping using H2 embedded database
    - maven build
    - unit tests

    Optional
    - Implement in Groovy
    - Secured via Spring Security HTTP Basic
    - JSR-303 Bean Validation
  version: 1.0.0
  license:
    name: Apache 2.0
    url: http://www.apache.org/licenses/LICENSE-2.0.html

paths:
  /inventory:
    get:
      description: List available inventory in the system.
      produces:
        - application/json
      parameters:
        - in: query
          name: skip
          description: number of records to skip for pagination
          type: integer
          format: int32
          minimum: 0
          required: false
        - in: query
          name: limit
          description: maximum number of records to return
          type: integer
          format: int32
          minimum: 0
          maximum: 50
          required: false
      responses:
        200:
          description: list of inventory
          schema:
            type: array
            items:
              $ref: '#/definitions/InventoryItem'
        400:
          description: bad input parameter
    post:
      description: Adds an item to the system
      consumes:
        - application/json
      produces:
        - application/json
      parameters:
        - in: body
          name: inventoryItem
          description: Inventory item to add
          schema:
            $ref: '#/definitions/InventoryItem'
      responses:
        201:
          description: item created
        400:
          description: invalid input, object invalid
        409:
          description: an existing item already exists
  /inventory/{id}:
    get:
      description: Returns an inventory item by id.
      produces:
        - application/json
      parameters:
        - in: path
          name: id
          type: string
          format: uuid
          required: true
      responses:
        200:
          description: an inventory item
          schema:
            $ref: '#/definitions/InventoryItem'
        404:
          description: not found
definitions:
  InventoryItem:
    type: object
    required:
      - id
      - name
      - manufacturer
      - releaseDate
    properties:
      id:
        type: string
        format: uuid
        example: "d290f1ee-6c54-4b01-90e6-d701748f0851"
      name:
        type: string
        example: Widget Adapter
      releaseDate:
        type: string
        format: date-time
        example: "2016-08-29T09:12:33.001Z"
      manufacturer:
        $ref: '#/definitions/Manufacturer'
  Manufacturer:
    required:
      - name
    properties:
      name:
        type: string
        example: ACME Corporation
      homePage:
        type: string
        format: url
        example:  https://www.acme-corp.com
      phone:
        type: string
        example: "(07) 5556 4321"
```

## Sample 200 Response
```
[
    {
        "id": 1,
        "name": "apple",
        "manufacturer": {
            "man_name": "Big Fat Corp",
            "url": "666-666",
            "phone": "http://www.bigfatcorp.com"
        },
        "releaseDate": "10/10/20"
    },
    {
        "id": 2,
        "name": "pair",
        "manufacturer": {
            "man_name": "Big Fat Corp",
            "url": "666-666",
            "phone": "http://www.bigfatcorp.com"
        },
        "releaseDate": "11/10/20"
    }
]
```

## Sample ERROR Response
```
{
    "timestamp": "01-07-2020 07:49:24",
    "status": "400",
    "error": "BAD_REQUEST",
    "message": "Max value for limit is 50",
    "path": "/inventory"
}
```

## Deliverables
  - Spring MVC REST controller with mock mvc test
    - JPA mapping using H2 embedded database
    - maven build
    - unit tests
    
 Note : Due to time, unable to include all unit tests
