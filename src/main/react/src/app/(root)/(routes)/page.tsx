"use client"

import React, {Suspense} from 'react';
import {Companions} from "@/components/companions";
import {Categories} from "@/components/categories";
import {SearchInput} from "@/components/search-input";

interface RootPageProps {
    searchParams: {
        categoryId?: string;
        name?: string;
    };
}

const RootPage = async ({searchParams}: RootPageProps) => {
    let companions = null;
    let categories = null;
    const headers = {
        'Content-Type': 'application/json'
    };
    const params = {
        id: '',
        name: ''
    };
    const queryString = new URLSearchParams(params).toString();
    let url = process.env.NEXT_PUBLIC_BACKEND_URL;
    console.log(url);
    if(!url)
        url = "";

    const fetchData = async () => {
        try {
            return fetch(url + `/companion?${queryString}`, {
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
    const fetchCategories = async () => {
        try {
            return fetch(url + `/companion/categories`, {
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
        categories = await fetchCategories();
    } catch (e) {
        companions = [];
        categories = [];
        console.log(e);
    }
    return (
        <div className="h-full p-4 space-y-2">
            <Suspense fallback={<div>Loading...</div>}>
                <SearchInput/>
                <Categories data={categories}/>
                <Companions data={companions}/>
            </Suspense>
        </div>
    );
};

export default RootPage;
