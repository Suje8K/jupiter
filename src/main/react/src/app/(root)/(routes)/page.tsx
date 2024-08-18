"use client"

import React, {Suspense} from 'react';
import {Companions} from "@/components/companions";
import {SearchInput} from "@/components/search-input";

interface RootPageProps {
    searchParams: {
        categoryId?: string;
        name?: string;
    };
}

const RootPage = async ({searchParams}: RootPageProps) => {
    let companions = null;
    const fetchData = async () => {
        const headers = {
            'Content-Type': 'application/json'
        };
        const params = {
            id: '',
            name: ''
        };
        const queryString = new URLSearchParams(params).toString();
        try {
            return fetch(`http://127.0.0.1:8080/api/v1/companion?${queryString}`, {
                method: 'GET',
                headers: headers,
            }).then(response => {
                const contentType = response.headers.get('Content-Type');
                if (contentType && contentType.includes('application/json')) {
                    return response.json();
                } else {
                    return [];
                }
            });
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            return [];
        }
    }
    try {
        companions = await fetchData();
    } catch (e) {
        companions = [];
        console.log(e);
    }
    return (
        <div className="h-full p-4 space-y-2">
            <Suspense fallback={<div>Loading...</div>}>
                <SearchInput/>
                {/*<Categories data={categories}/>*/}
                <Companions data={companions}/>
            </Suspense>
        </div>
    );
};

export default RootPage;
