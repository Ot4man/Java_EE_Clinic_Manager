// Initialize Lucide Icons
lucide.createIcons();

document.addEventListener('DOMContentLoaded', () => {
    const testBtn = document.getElementById('testBtn');
    const responseArea = document.getElementById('responseArea');

    testBtn.addEventListener('click', async () => {
        const originalText = testBtn.innerHTML;
        
        // Loading state
        testBtn.innerHTML = '<i data-lucide="loader" class="lucide-spin"></i> Testing...';
        lucide.createIcons();
        testBtn.disabled = true;
        
        responseArea.textContent = 'Sending request to /hello-servlet...';

        try {
            // Simulate slight network delay for UI feel if local is too fast
            const start = Date.now();
            
            const response = await fetch('hello-servlet');
            
            // Artificial delay to show loading state (optional, just for demo)
            const elapsed = Date.now() - start;
            if (elapsed < 500) await new Promise(r => setTimeout(r, 500 - elapsed));

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            
            const text = await response.text();
            
            // Format response output
            const timestamp = new Date().toISOString();
            responseArea.innerHTML = `<span style="color: #94A3B8;">[${timestamp}]</span> <span style="color: #10B981;">SUCCESS</span>\n\nResponse from server:\n${text}`;
            
        } catch (error) {
            const timestamp = new Date().toISOString();
            responseArea.innerHTML = `<span style="color: #94A3B8;">[${timestamp}]</span> <span style="color: #EF4444;">ERROR</span>\n\nFailed to fetch:\n${error.message}`;
            responseArea.style.color = 'var(--danger)';
        } finally {
            // Reset button
            testBtn.innerHTML = originalText;
            lucide.createIcons();
            testBtn.disabled = false;
        }
    });
});

// Add rotation animation for loader
const style = document.createElement('style');
style.textContent = `
    .lucide-spin {
        animation: spin 2s linear infinite;
    }
    @keyframes spin {
        100% { transform: rotate(360deg); }
    }
`;
document.head.appendChild(style);
