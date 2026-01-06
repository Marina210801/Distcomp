Write-Host "=== Testing API endpoints ===" -ForegroundColor Cyan

# 1. Test endpoint
Write-Host "`n1. Testing /test endpoint..." -ForegroundColor Yellow
try {
    $result = Invoke-RestMethod "http://localhost:24110/test"
    Write-Host "SUCCESS: $result" -ForegroundColor Green
} catch {
    Write-Host "ERROR: $_" -ForegroundColor Red
}

# 2. Get all editors
Write-Host "`n2. Testing GET /api/v1.0/editors..." -ForegroundColor Yellow
try {
    $editors = Invoke-RestMethod "http://localhost:24110/api/v1.0/editors"
    Write-Host "SUCCESS: Found $($editors.Count) editors" -ForegroundColor Green
} catch {
    Write-Host "ERROR: $_" -ForegroundColor Red
}

# 3. Create editor
Write-Host "`n3. Testing POST /api/v1.0/editors..." -ForegroundColor Yellow
try {
    $body = @{
        login = "maryaagu@mail.ru"
        password = "password123"
        firstname = "Marina"
        lastname = "Dorofey"
    } | ConvertTo-Json
    
    $response = Invoke-RestMethod "http://localhost:24110/api/v1.0/editors" -Method Post -Body $body -ContentType "application/json"
    Write-Host "SUCCESS: Created editor with ID $($response.id)" -ForegroundColor Green
} catch {
    Write-Host "ERROR: $_" -ForegroundColor Red
}